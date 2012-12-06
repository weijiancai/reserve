/**
 * Created with IntelliJ IDEA.
 * User: WangXinghao
 * Date: 12-12-6
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
package {
    public class ProcessCanvasTest {
        public function ProcessCanvasTest() {
        }

        [Test]
        public function testDraw():void {

        }

        [Test]
        public function testXmlNamespace():void {
            var xml:XML = getXml();
            trace(xml.namespaceDeclarations().length);
        }

        private function getXml():XML {
            return XML("<definitions joinwork:updateTime=\"1331283630824\" exporter=\"Joinwork Process Studio\" exporterVersion=\"3.1.0\"\n" +
                            "             targetNamespace=\"http://www.ygsoft.com/\" id=\"leaveApplicationAndBacklog\" name=\"请假申请--条件分支合并\"\n" +
                            "             xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\"\n" +
                            "             xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\"\n" +
                            "             xmlns:joinwork=\"http://www.joinwork.net/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                            "             xmlns:activiti=\"http://activiti.org/bpmn\">\n" +
                            "\n" +
                            "    <process id=\"条件分支与合并流程\" name=\"条件分支与合并流程\">\n" +
                            "        <laneSet id=\"LaneSet_1\" name=\"LaneSet_1\">\n" +
                            "            <lane id=\"Lane_1\" name=\"业务部门\">\n" +
                            "                <flowNodeRef>Start_1</flowNodeRef>\n" +
                            "                <flowNodeRef>Task_1</flowNodeRef>\n" +
                            "                <flowNodeRef>Gateway_1</flowNodeRef>\n" +
                            "                <flowNodeRef>Task_2</flowNodeRef>\n" +
                            "                <flowNodeRef>Task_3</flowNodeRef>\n" +
                            "                <flowNodeRef>Gateway_2</flowNodeRef>\n" +
                            "                <flowNodeRef>Gateway_4</flowNodeRef>\n" +
                            "            </lane>\n" +
                            "            <lane id=\"Lane_2\" name=\"人力资源部\">\n" +
                            "                <flowNodeRef>Task_4</flowNodeRef>\n" +
                            "            </lane>\n" +
                            "            <lane id=\"Lane_3\" name=\"考勤系统\">\n" +
                            "                <flowNodeRef>Task_5</flowNodeRef>\n" +
                            "                <flowNodeRef>Gateway_3</flowNodeRef>\n" +
                            "                <flowNodeRef>End_1</flowNodeRef>\n" +
                            "            </lane>\n" +
                            "        </laneSet>\n" +
                            "        <startEvent id=\"Start_1\" name=\"开始\">\n" +
                            "            <outgoing>SequenceFlow_1</outgoing>\n" +
                            "        </startEvent>\n" +
                            "        <userTask id=\"Task_1\" name=\"填写请假申请单\">\n" +
                            "            <incoming>SequenceFlow_1</incoming>\n" +
                            "            <outgoing>SequenceFlow_2</outgoing>\n" +
                            "            <humanPerformer>\n" +
                            "                <resourceAssignmentExpression>\n" +
                            "                    <formalExpression>zhangsan</formalExpression>\n" +
                            "                </resourceAssignmentExpression>\n" +
                            "            </humanPerformer>\n" +
                            "        </userTask>\n" +
                            "        <exclusiveGateway id=\"Gateway_1\" name=\"假期时长\" gatewayDirection=\"Diverging\">\n" +
                            "            <incoming>SequenceFlow_2</incoming>\n" +
                            "            <outgoing>SequenceFlow_3</outgoing>\n" +
                            "            <outgoing>SequenceFlow_4</outgoing>\n" +
                            "        </exclusiveGateway>\n" +
                            "        <userTask id=\"Task_2\" name=\"部门经理审批\">\n" +
                            "            <incoming>SequenceFlow_3</incoming>\n" +
                            "            <outgoing>SequenceFlow_5</outgoing>\n" +
                            "            <humanPerformer>\n" +
                            "                <resourceAssignmentExpression>\n" +
                            "                    <formalExpression>lisi</formalExpression>\n" +
                            "                </resourceAssignmentExpression>\n" +
                            "            </humanPerformer>\n" +
                            "        </userTask>\n" +
                            "        <userTask id=\"Task_3\" name=\"基层经理审批\">\n" +
                            "            <incoming>SequenceFlow_4</incoming>\n" +
                            "            <outgoing>SequenceFlow_6</outgoing>\n" +
                            "            <humanPerformer>\n" +
                            "                <resourceAssignmentExpression>\n" +
                            "                    <formalExpression>wangwu</formalExpression>\n" +
                            "                </resourceAssignmentExpression>\n" +
                            "            </humanPerformer>\n" +
                            "        </userTask>\n" +
                            "        <parallelGateway id=\"Gateway_2\" name=\"转备案\" gatewayDirection=\"Diverging\">\n" +
                            "            <incoming>SequenceFlow_12</incoming>\n" +
                            "            <outgoing>SequenceFlow_7</outgoing>\n" +
                            "            <outgoing>SequenceFlow_8</outgoing>\n" +
                            "        </parallelGateway>\n" +
                            "        <userTask id=\"Task_4\" name=\"人力专员确认\">\n" +
                            "            <incoming>SequenceFlow_7</incoming>\n" +
                            "            <outgoing>SequenceFlow_9</outgoing>\n" +
                            "            <humanPerformer>\n" +
                            "                <resourceAssignmentExpression>\n" +
                            "                    <formalExpression>maliu</formalExpression>\n" +
                            "                </resourceAssignmentExpression>\n" +
                            "            </humanPerformer>\n" +
                            "        </userTask>\n" +
                            "        <serviceTask id=\"Task_5\" name=\"自动备案\" activiti:class=\"com.ygsoft.process.AutoRecordActivityDelegate\">\n" +
                            "            <incoming>SequenceFlow_8</incoming>\n" +
                            "            <outgoing>SequenceFlow_10</outgoing>\n" +
                            "        </serviceTask>\n" +
                            "        <parallelGateway id=\"Gateway_3\" name=\"合并\" gatewayDirection=\"Converging\">\n" +
                            "            <incoming>SequenceFlow_9</incoming>\n" +
                            "            <incoming>SequenceFlow_10</incoming>\n" +
                            "            <outgoing>SequenceFlow_11</outgoing>\n" +
                            "        </parallelGateway>\n" +
                            "        <endEvent id=\"End_1\" name=\"结束\">\n" +
                            "            <incoming>SequenceFlow_11</incoming>\n" +
                            "        </endEvent>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_1\" sourceRef=\"Start_1\" targetRef=\"Task_1\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_2\" sourceRef=\"Task_1\" targetRef=\"Gateway_1\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_3\" sourceRef=\"Gateway_1\" targetRef=\"Task_2\">\n" +
                            "            <conditionExpression><![CDATA[${order.days >= 3}]]></conditionExpression>\n" +
                            "        </sequenceFlow>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_4\" sourceRef=\"Gateway_1\" targetRef=\"Task_3\">\n" +
                            "            <conditionExpression><![CDATA[${order.days < 3}]]></conditionExpression>\n" +
                            "        </sequenceFlow>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_5\" sourceRef=\"Task_2\" targetRef=\"Gateway_4\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_7\" sourceRef=\"Gateway_2\" targetRef=\"Task_4\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_9\" sourceRef=\"Task_4\" targetRef=\"Gateway_3\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_10\" sourceRef=\"Task_5\" targetRef=\"Gateway_3\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_11\" sourceRef=\"Gateway_3\" targetRef=\"End_1\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_8\" sourceRef=\"Gateway_2\" targetRef=\"Task_5\"/>\n" +
                            "        <exclusiveGateway id=\"Gateway_4\" name=\"合并网关\" gatewayDirection=\"Converging\">\n" +
                            "            <incoming>SequenceFlow_5</incoming>\n" +
                            "            <incoming>SequenceFlow_6</incoming>\n" +
                            "            <outgoing>SequenceFlow_12</outgoing>\n" +
                            "        </exclusiveGateway>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_12\" sourceRef=\"Gateway_4\" targetRef=\"Gateway_2\"/>\n" +
                            "        <sequenceFlow id=\"SequenceFlow_6\" sourceRef=\"Task_3\" targetRef=\"Gateway_4\"/>\n" +
                            "    </process>\n" +
                            "    <bpmndi:BPMNDiagram id=\"ProcessDiagram_1\" name=\"ProcessDiagram_1\">\n" +
                            "        <bpmndi:BPMNPlane bpmnElement=\"条件分支与合并流程\">\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Start_1\" id=\"Shape_1\">\n" +
                            "                <dc:Bounds height=\"22\" width=\"22\" x=\"103\" y=\"135\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Task_1\" id=\"Shape_2\">\n" +
                            "                <dc:Bounds height=\"50\" width=\"80\" x=\"173\" y=\"121\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_1\" id=\"Connector_1\">\n" +
                            "                <di:waypoint x=\"126\" y=\"146\"/>\n" +
                            "                <di:waypoint x=\"172\" y=\"146\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Gateway_1\" id=\"Shape_4\" isMarkerVisible=\"false\">\n" +
                            "                <dc:Bounds height=\"30\" width=\"38\" x=\"294\" y=\"131\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Task_2\" id=\"Shape_5\">\n" +
                            "                <dc:Bounds height=\"50\" width=\"80\" x=\"375\" y=\"76\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Task_3\" id=\"Shape_6\">\n" +
                            "                <dc:Bounds height=\"50\" width=\"80\" x=\"372\" y=\"170\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_2\" id=\"Connector_2\">\n" +
                            "                <di:waypoint x=\"254\" y=\"146\"/>\n" +
                            "                <di:waypoint x=\"293\" y=\"146\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_3\" id=\"Connector_3\">\n" +
                            "                <di:waypoint x=\"313\" y=\"130\"/>\n" +
                            "                <di:waypoint x=\"313\" y=\"99\"/>\n" +
                            "                <di:waypoint x=\"313\" y=\"99\"/>\n" +
                            "                <di:waypoint x=\"313\" y=\"99\"/>\n" +
                            "                <di:waypoint x=\"367\" y=\"99\"/>\n" +
                            "                <di:waypoint x=\"374\" y=\"99\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_4\" id=\"Connector_4\">\n" +
                            "                <di:waypoint x=\"313\" y=\"162\"/>\n" +
                            "                <di:waypoint x=\"313\" y=\"200\"/>\n" +
                            "                <di:waypoint x=\"371\" y=\"200\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Gateway_2\" id=\"Shape_7\">\n" +
                            "                <dc:Bounds height=\"30\" width=\"38\" x=\"634\" y=\"133\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Task_4\" id=\"Shape_8\">\n" +
                            "                <dc:Bounds height=\"50\" width=\"80\" x=\"613\" y=\"264\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_5\" id=\"Connector_5\">\n" +
                            "                <di:waypoint x=\"456\" y=\"99\"/>\n" +
                            "                <di:waypoint x=\"506\" y=\"99\"/>\n" +
                            "                <di:waypoint x=\"506\" y=\"132\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_7\" id=\"Connector_7\">\n" +
                            "                <di:waypoint x=\"653\" y=\"164\"/>\n" +
                            "                <di:waypoint x=\"653\" y=\"263\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Task_5\" id=\"Shape_9\">\n" +
                            "                <dc:Bounds height=\"50\" width=\"80\" x=\"695\" y=\"352\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Lane_1\" id=\"LaneCom_1\" isHorizontal=\"true\">\n" +
                            "                <dc:Bounds height=\"167\" width=\"909\" x=\"3\" y=\"60\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Lane_2\" id=\"LaneCom_2\" isHorizontal=\"true\">\n" +
                            "                <dc:Bounds height=\"100\" width=\"910\" x=\"3\" y=\"227\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Lane_3\" id=\"LaneCom_3\" isHorizontal=\"true\">\n" +
                            "                <dc:Bounds height=\"179\" width=\"910\" x=\"2\" y=\"327\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Gateway_3\" id=\"Shape_3\">\n" +
                            "                <dc:Bounds height=\"30\" width=\"38\" x=\"634\" y=\"425\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_9\" id=\"Connector_9\">\n" +
                            "                <di:waypoint x=\"653\" y=\"315\"/>\n" +
                            "                <di:waypoint x=\"653\" y=\"363\"/>\n" +
                            "                <di:waypoint x=\"653\" y=\"424\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_10\" id=\"Connector_10\">\n" +
                            "                <di:waypoint x=\"735\" y=\"403\"/>\n" +
                            "                <di:waypoint x=\"673\" y=\"440\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"End_1\" id=\"Shape_10\">\n" +
                            "                <dc:Bounds height=\"22\" width=\"22\" x=\"395\" y=\"429\"/>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_11\" id=\"Connector_11\">\n" +
                            "                <di:waypoint x=\"633\" y=\"440\"/>\n" +
                            "                <di:waypoint x=\"418\" y=\"440\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_8\" id=\"Connector_8\">\n" +
                            "                <di:waypoint x=\"673\" y=\"143\"/>\n" +
                            "                <di:waypoint x=\"735\" y=\"143\"/>\n" +
                            "                <di:waypoint x=\"735\" y=\"351\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNShape bpmnElement=\"Gateway_4\" id=\"Shape_11\" isMarkerVisible=\"false\">\n" +
                            "                <dc:Bounds height=\"30\" width=\"38\" x=\"487\" y=\"133\"/>\n" +
                            "                <bpmndi:BPMNLabel>\n" +
                            "                    <dc:Bounds height=\"15\" width=\"80\" x=\"466\" y=\"165\"/>\n" +
                            "                </bpmndi:BPMNLabel>\n" +
                            "            </bpmndi:BPMNShape>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_12\" id=\"Connector_12\">\n" +
                            "                <di:waypoint x=\"526\" y=\"148\"/>\n" +
                            "                <di:waypoint x=\"525\" y=\"148\"/>\n" +
                            "                <di:waypoint x=\"633\" y=\"148\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "            <bpmndi:BPMNEdge bpmnElement=\"SequenceFlow_6\" id=\"Connector_6\">\n" +
                            "                <di:waypoint x=\"453\" y=\"200\"/>\n" +
                            "                <di:waypoint x=\"506\" y=\"200\"/>\n" +
                            "                <di:waypoint x=\"506\" y=\"164\"/>\n" +
                            "            </bpmndi:BPMNEdge>\n" +
                            "        </bpmndi:BPMNPlane>\n" +
                            "    </bpmndi:BPMNDiagram>\n" +
                            "</definitions>");
        }
    }
}
