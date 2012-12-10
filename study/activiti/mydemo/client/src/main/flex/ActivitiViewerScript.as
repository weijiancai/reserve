import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.ResultEvent;

private var processDefineXml:XML;
private var processEngineName:String;
private var processDefineId:String;
private var xmlFile:FileReference;

private function init():void {
    processEngineService.send();

    Security.allowDomain("*");

    xmlFile = new FileReference();
    xmlFile.addEventListener(Event.SELECT, selectFileHandler);
    xmlFile.addEventListener(Event.COMPLETE, completeHandler);
}

private static function selectFileHandler(event:Event):void {
    var file:FileReference = FileReference(event.target);
    file.load();
}

private function completeHandler(event:Event):void {
    processDefineXml = XML(event.target.data);
    ta_xmlStr.text = processDefineXml.toXMLString();
    drawProcessImage();
    processCanvas.draw(processDefineXml);
}

private function handleProcessEngineTreeClick():void {
    if (processEngineTree.selectedItem == null) {
        return;
    }
    trace(processEngineTree.selectedItem.@id);
    if (processEngineTree.getParentItem(processEngineTree.selectedItem) == null) { // 流程引擎对象
        // 清空数据
        dg_processEngine.dataProvider = null;
        // 选中，并添加数据
        propAccordion.selectedIndex = 0;
        processEngineName = processEngineTree.selectedItem.@id;
        addItemToDataGrid(dg_processEngine, processEngineTree.selectedItem as XML);

        // 请求流程引擎配置信息
        processEngineConfigurationService.request.processEngineName = processEngineName;
        processEngineConfigurationService.send();

        // 请求用户组信息
        groupService.request.processEngineName = processEngineName;
        groupService.send();

        // 请求用户信息
        userService.request.processEngineName = processEngineName;
        userService.send();
    } else { // 流程定义对象
        // 清除数据
        dg_processDefinition.dataProvider = null;
        dg_historicActivityInstance.dataProvider = null;
        dg_historicTaskInstance.dataProvider = null;
        dg_historicDetail.dataProvider = null;

        historyNavigator.selectedIndex = 0;
        propAccordion.selectedIndex = 1;
        processEngineName = processEngineTree.getParentItem(processEngineTree.selectedItem).@id;
        processDefineId = encodeURI(processEngineTree.selectedItem.@id);

        // 请求流程图
        processImage.source = "http://localhost:8088/process?method=getImage&processEngineName=" + processEngineName + "&processDefineId=" + processDefineId;
        processDefXml.send();

        // 请求流程定义
        processDefineService.request.processEngineName = processEngineName;
        processDefineService.request.processDefineId = processDefineId;
        processDefineService.send();

        // 请求流程实例
        requestProcessInstance();

        // 获取流程实例历史信息
        dg_historicProcessInstance.dataProvider = null;
        historicProcessInstanceService.request.processEngineName = processEngineName;
        historicProcessInstanceService.request.processDefineId = processDefineId;
        historicProcessInstanceService.send();
    }
}

private static function addItemToDataGrid(dg:DataGrid, xml:XML):void {
    var attNamesList:XMLList = xml.@*;
    var dataProvider:Object = dg.dataProvider;
    var ac:ArrayCollection;
    if (dataProvider == null) {
        ac = new ArrayCollection();
    } else {
        ac = dataProvider as ArrayCollection;
    }

    for (var i:int = 0; i < attNamesList.length(); i++) {
        ac.addItem({name:attNamesList[i].localName(), value:attNamesList[i].toString()});
    }
    dg.dataProvider = ac;
}

private function handlerProcessEngineConfigurationService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    addItemToDataGrid(dg_processEngine, xml.children()[0]);
}

private function handlerProcessDefineService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    addItemToDataGrid(dg_processDefinition, xml.children()[0]);
}

private function handlerProcessInstanceService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_processInstance.dataProvider = xml.children();
}

private function handleProcessInstanceDgClick():void {
    var processInstanceId:String = dg_processInstance.selectedItem.@id;

    propAccordion.selectedIndex = 2;
    historyNavigator.selectedIndex = 1;

    // 请求活动流程图
    processImage.source = "http://localhost:8088/process?method=getActiveImage&processEngineName=" + processEngineName + "&processInstanceId=" + processInstanceId;

    // 获取流程实例变量
    dg_variables.dataProvider = null;
    processInstanceVariableService.request.processEngineName = processEngineName;
    processInstanceVariableService.request.processInstanceId = processInstanceId;
    processInstanceVariableService.send();

    // 获取流程实例任务
    dg_tasks.dataProvider = null;
    processInstanceTaskService.request.processEngineName = processEngineName;
    processInstanceTaskService.request.processInstanceId = processInstanceId;
    processInstanceTaskService.send();

    // 获取活动实例历史信息
    dg_historicActivityInstance.dataProvider = null;
    historicActivityInstanceService.request.processEngineName = processEngineName;
    historicActivityInstanceService.request.processInstanceId = processInstanceId;
    historicActivityInstanceService.send();

    // 获取流程实例历史信息
    dg_historicProcessInstance.dataProvider = null;
    historicProcessInstanceService.request.processEngineName = processEngineName;
    historicProcessInstanceService.request.processInstanceId = processInstanceId;
    historicProcessInstanceService.send();

    // 获取任务实例历史信息
    dg_historicProcessInstance.dataProvider = null;
    historicTaskInstanceService.request.processEngineName = processEngineName;
    historicTaskInstanceService.request.processInstanceId = processInstanceId;
    historicTaskInstanceService.send();

    // 获取历史详细信息
    dg_historicDetail.dataProvider = null;
    historicDetailService.request.processEngineName = processEngineName;
    historicDetailService.request.processInstanceId = processInstanceId;
    historicDetailService.send();

    // 流程实例Execution
    dg_ProcessInstanceExecution.dataProvider = null;
    processInstanceExecutionService.request.processEngineName = processEngineName;
    processInstanceExecutionService.request.processInstanceId = processInstanceId;
    processInstanceExecutionService.send();
}

private function processDefXmlHandler(event:ResultEvent):void {
    processDefineXml = XML(event.result);
    drawProcessImage();
    ta_xmlStr.text = processDefineXml.toXMLString();
    processCanvas.draw(processDefineXml);
}

private function drawProcessImage():void {
    // 清空画布
    this.canvas.graphics.clear();

    var model:Namespace = processDefineXml.namespace();
    var bpmndi:Namespace = processDefineXml.namespace("bpmndi");
    var dc:Namespace = processDefineXml.namespace("dc");
    var di:Namespace = processDefineXml.namespace("di");

    var i:int;
    var startEventArray:Array = [];
    var endEventArray:Array = [];
    var userTaskArray:Array = [];
    var exclusiveGatewayArray:Array = [];
    var parallelGatewayArray:Array = [];
    var serviceTaskArray:Array = [];


    var processNodeList:XMLList = processDefineXml.model::process.*;
    var node:XML;

    for (i = 0; i < processNodeList.length(); i++) {
        node = processNodeList[i];
//                Alert.show(node.localName());
        if ("startEvent" == node.localName()) {
            startEventArray.push(node.@id.toString());
        } else if ("endEvent" == node.localName()) {
            endEventArray.push(node.@id.toString());
        } else if ("userTask" == node.localName()) {
            userTaskArray.push(node.@id.toString());
        } else if ("exclusiveGateway" == node.localName()) {
            exclusiveGatewayArray.push(node.@id.toString());
        } else if ("parallelGateway" == node.localName()) {
            parallelGatewayArray.push(node.@id.toString());
        } else if ("serviceTask" == node.localName()) {
            serviceTaskArray.push(node.@id.toString());
        }
    }
//            Alert.show(startEventArray.join());

    var xmlList:XMLList = processDefineXml.bpmndi::BPMNDiagram..bpmndi::BPMNShape; // 图形
//            Alert.show(xmlList.length());

    var baseX:Number = 0;
    var baseY:Number = -50;
    trace("basX = " + baseX + ", basY = " + baseY);
    var x:Number, y:Number, width:Number, height:Number, bpmnElement:String;
    for (i = 0; i < xmlList.length(); i++) {
        bpmnElement = xmlList[i].@bpmnElement;
        x = baseX + Number(xmlList[i].dc::Bounds.@x);
        y = baseY + Number(xmlList[i].dc::Bounds.@y);
        width = Number(xmlList[i].dc::Bounds.@width);
        height = Number(xmlList[i].dc::Bounds.@height);

        if (startEventArray.indexOf(bpmnElement) != -1) { // 开始节点
//                    Alert.show("Draw startEvent : " + bpmnElement)
            canvas.graphics.lineStyle(1, 0x000000);
            canvas.graphics.drawCircle(x + width / 2, y + height / 2, 15);
        } else if (endEventArray.indexOf(bpmnElement) != -1) { // 结束节点
//                    Alert.show("Draw endEvent : " + bpmnElement)
            canvas.graphics.lineStyle(4, 0x000000);
            canvas.graphics.drawCircle(x + width / 2, y + height / 2, 15);
        } else if (exclusiveGatewayArray.indexOf(bpmnElement) != -1) {
            canvas.graphics.lineStyle(1, 0x123456);
            drawRhombus(canvas.graphics, x, y, width, height);
        }

        else {
//                    Alert.show("Draw Rect : " + bpmnElement)
            canvas.graphics.lineStyle(1, 0x123456);
            canvas.graphics.drawRect(x, y, width, height);
        }
//                Alert.show(processNode.length());
//                Alert.show(processNode.name().toString());
    }

    xmlList = processDefineXml.bpmndi::BPMNDiagram..bpmndi::BPMNEdge; // 画线
    for (i = 0; i < xmlList.length(); i++) {
        canvas.graphics.lineStyle(1, 0x000000);

        var waypointList:XMLList = xmlList[i].di::waypoint;
        var startX:Number = 0, startY:Number = 0;
        for (var j:int = 0; j < waypointList.length(); j++) {
            if (startX > 0 && startY > 0) {
                canvas.graphics.moveTo(startX, startY);
                canvas.graphics.lineTo(baseX + Number(waypointList[j].@x), baseY + Number(waypointList[j].@y));
            }

            startX = baseX + Number(waypointList[j].@x);
            startY = baseY + Number(waypointList[j].@y);
        }
    }
}

// 画菱形
private static function drawRhombus(graphics:Graphics, x:Number, y:Number, width:Number, height:Number):void {
    graphics.moveTo(x, y + height / 2);
    graphics.lineTo(x + width / 2, y);
    graphics.lineTo(x + width, y + height / 2);
    graphics.lineTo(x + width / 2, y + height);
    graphics.lineTo(x, y + height / 2);
}

private function startProcess():void {
    if (processEngineName != null && processDefineId != null) {
        startProcessService.request.processEngineName = processEngineName;
        startProcessService.request.processDefineId = processDefineId;
        startProcessService.send();
    }
}

private function startProcessHandler(event:ResultEvent):void {
    // 请求流程实例
    requestProcessInstance();
}

private function handlerVariablesService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_variables.dataProvider = xml.children();
}

private function handlerProcessInstanceTasksService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_tasks.dataProvider = xml.children();
    for(var i:int = 0; i < xml.children().length(); i++) {
        processCanvas.highlightNode(xml.children()[i].@taskDefinitionKey.toString());
    }
}

private function handleTasksDgClick():void {
    propAccordion.selectedIndex = 2;

    // 获取流程实例任务变量
    dg_variables.dataProvider = null;
    taskVariableService.request.processEngineName = processEngineName;
    taskVariableService.request.taskId = dg_tasks.selectedItem.@id;
    taskVariableService.send();
}

private function handlerHistoricActivityInstanceService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_historicActivityInstance.dataProvider = xml.children();
}

private function handlerHistoricProcessInstanceService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_historicProcessInstance.dataProvider = xml.children();
}

private function handlerHistoricTaskInstanceService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_historicTaskInstance.dataProvider = xml.children();
}

private function handlerHistoricDetailService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_historicDetail.dataProvider = xml.children();
}

private function requestProcessInstance():void {
    dg_processInstance.dataProvider = null;
    processInstanceService.request.processEngineName = processEngineName;
    processInstanceService.request.processDefineId = processDefineId;
    processInstanceService.send();
}

private function openFile():void {
    xmlFile.browse(getTypeFilter());
}

private static function getTypeFilter():Array {
    var imagesFilter:FileFilter = new FileFilter("BPMN 2.0 Xml (*.xml)", "*.xml");
    return [imagesFilter];
}

private function deployProcess():void {

}

private function handlerProcessInstanceExecutionService(event:ResultEvent):void {
    var xml:XML = XML(event.result);
    dg_ProcessInstanceExecution.dataProvider = xml.children();
}

private function handleGroupTreeClick():void {
    if (groupTree.selectedItem == null) {
        return;
    }

    propAccordion.selectedIndex = 3;
    dg_groups.dataProvider = null;
    addItemToDataGrid(dg_groups, groupTree.selectedItem as XML);

    var groupId:String = groupTree.selectedItem.@id;
    // 请求此用户组下的所有用户信息
    userService.request.processEngineName = processEngineName;
    userService.request.groupId = groupId;
    userService.send();

    // 获取用户组的任务列表
    dg_myTasks.dataProvider = null;
    myTaskService.request.processEngineName = processEngineName;
    myTaskService.request.groupId = groupId;
    myTaskService.send();
}

private function handleUserTreeClick():void {
    if (userTree.selectedItem == null) {
        return;
    }

    propAccordion.selectedIndex = 4;
    dg_users.dataProvider = null;
    addItemToDataGrid(dg_users, userTree.selectedItem as XML);

    var userId:String = userTree.selectedItem.@id;
    // 获取用户的任务列表
    dg_myTasks.dataProvider = null;
    myTaskService.request.processEngineName = processEngineName;
    myTaskService.request.userId = userId;
    myTaskService.send();
}