/**
 *
 * @author weijiancai
 */
package {
    import activitiviewer.event.IconEvent;

    import flash.display.Bitmap;

    import flash.display.BitmapData;
    import flash.display.Graphics;
    import flash.display.Loader;
    import flash.display.Sprite;
    import flash.events.Event;

    import flash.events.MouseEvent;
    import flash.geom.Matrix;
    import flash.net.URLRequest;
    import flash.text.TextField;
    import flash.text.TextFieldAutoSize;

    import mx.controls.Alert;
    import mx.controls.Button;

    import mx.controls.Image;
    import mx.controls.Label;
    import mx.core.BitmapAsset;
    import mx.effects.Glow;

    [Event(name = IconEvent.ICON_MOUSE_DOWN, type = "activitiviewer.event.IconEvent")]
    [Event(name = IconEvent.ICON_MOUSE_UP, type = "activitiviewer.event.IconEvent")]
    [Event(name = IconEvent.ICON_MOVE, type = "activitiviewer.event.IconEvent")]

    public class ProcessIcon extends Image {
        public static const TYPE_START_EVENT:String = "startEvent";
        public static const TYPE_END_EVENT:String = "endEvent";
        public static const TYPE_USER_TASK:String = "userTask";
        public static const TYPE_SERVICE_TASK:String = "serviceTask";
        public static const TYPE_EXCLUSIVE_GATEWAY:String = "exclusiveGateway";
        public static const TYPE_PARALLEL_GATEWAY:String = "parallelGateway";
        public static const TYPE_SEQUENCE_FLOW:String = "sequenceFlow";

        [Bindable]
        [Embed(source = "images/user.png")]
        public static var userTaskPng:Class;  // 用户任务

        [Bindable]
        [Embed(source = "images/service.png")]
        public var serviceTaskPng:Class;  // 服务任务

        // 名称
        private var _key:String;
        // 图标类型
        private var _type:String;
        // 图标上的文字说明
        private var _label:String;
        // 备注
        private var _memo:String;
        // 图标是否被选中
        public var isSelected:Boolean = false;
        // 图标是否高亮
        public var isHighlight:Boolean = false;
        // 图像数据
        private var _icon:Object;

        // X坐标
        private var _x:Number;
        // Y坐标
        private var _y:Number;
        // 宽度
        private var _width:Number;
        // 高度
        private var _height:Number;
        //箭头的大小
        public var radius:int = 5;

        private var text:TextField = new TextField();

        private var glow:Glow = new Glow();
        private var _di:XML;

        private var selectedRectBorder:IconBorder;
        private var highlightRectBorder:IconBorder;

        public function ProcessIcon(node:XML, di:XML) {
            this._di = di;

            if ("startEvent" == node.localName()) {
                _type = TYPE_START_EVENT;
            } else if ("endEvent" == node.localName()) {
                _type = TYPE_END_EVENT;
            } else if ("userTask" == node.localName()) {
                _type = TYPE_USER_TASK
            } else if ("exclusiveGateway" == node.localName()) {
                _type = TYPE_EXCLUSIVE_GATEWAY;
            } else if ("parallelGateway" == node.localName()) {
                _type = TYPE_PARALLEL_GATEWAY;
            } else if ("serviceTask" == node.localName()) {
                _type = TYPE_SERVICE_TASK;
            } else if ("sequenceFlow" == node.localName()) {
                _type = TYPE_SEQUENCE_FLOW;
            }

            _key = node.@id.toString();
            _label = node.@name.toString();

            if (di != null) {
                if ("BPMNShape" == di.localName()) {
                    _x = di.children()[0].@x;
                    _y = di.children()[0].@y;
                    _width = di.children()[0].@width;
                    _height = di.children()[0].@height;
                }
            }

            this.graphics.clear();

            text.autoSize = TextFieldAutoSize.LEFT;
            if (TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) { // 自动换行
                text.width = _width;
                text.wordWrap = true;
                text.condenseWhite = true;
                text.multiline = true;
            }

            glow.blurXFrom = 0;
            glow.blurXTo = 15;
            glow.color = 0xFF0000;
            glow.blurYFrom = 0;
            glow.blurYTo = 15;

            this.draw();
            this.bindEvents();
        }

        public function get key():String {
            return _key;
        }

        public function set key(value:String):void {
            _key = value;
        }

        public function get type():String {
            return _type;
        }

        public function set type(value:String):void {
            _type = value;
        }

        public function get label():String {
            return _label;
        }

        public function set label(value:String):void {
            _label = value;
        }

        public function get memo():String {
            return _memo;
        }

        public function set memo(value:String):void {
            _memo = value;
        }

        public function get icon():Object {
            return _icon;
        }

        public function set icon(value:Object):void {
            _icon = value;
        }

        // 画图标
        private function draw():void {
            if(this._icon == null) {
                trace("x = " + _x + ", y = " + _y);
                if(TYPE_USER_TASK == _type) {
                    this.x = _x + 5;
                    this.y = _y + 1;
                    this.source = userTaskPng;
                } else if(TYPE_SERVICE_TASK == _type) {
                    this.x = _x + 5;
                    this.y = _y + 1;
                    this.source = serviceTaskPng;
                }

                // 画文本
                if(TYPE_START_EVENT == this._type || TYPE_END_EVENT == _type || TYPE_EXCLUSIVE_GATEWAY == _type || TYPE_PARALLEL_GATEWAY == _type) {
                    text.x = _x - 3;
                    text.y = _y + 30 + 3;
                    this.addChild(text);
                } else {
                    text.x = -2;
                    text.y = 11;
                    this.addChild(text);
                }

                // 画圆角矩形
                if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                    this.graphics.lineStyle(0,0,1, true);
                    this.graphics.beginFill(0xffffff);
                    this.graphics.drawRoundRect(-5.5,  -1.5,  _width, _height, 20, 20);  // + 0.5抗锯齿
                    this.graphics.endFill();
                } else if(TYPE_EXCLUSIVE_GATEWAY == _type || TYPE_PARALLEL_GATEWAY == _type) { // 画菱形
                    this.graphics.lineStyle(1, 0x123456);
                    this.graphics.beginFill(0xffffff);
                    drawRhombus(this.graphics, _x, _y, _width, _height);
                    this.graphics.endFill();
                } else if(TYPE_SEQUENCE_FLOW == _type) { // 画线
                    this.graphics.lineStyle(1, 0x000000);
                    var waypointList:XMLList = _di.children();
                    var startX:Number = 0, startY:Number = 0, endX:Number = 0, endY:Number = 0;
                    for (var j:int = 0; j < waypointList.length(); j++) {
                        if (endX > 0 && endY > 0) {
                            this.graphics.moveTo(endX, endY);
                            this.graphics.lineTo(Number(waypointList[j].@x), Number(waypointList[j].@y));
                        }

                        endX = Number(waypointList[j].@x);
                        endY = Number(waypointList[j].@y);

                        if(j == waypointList.length() - 2) { // 记录最后一个开始点的位置
                            startX = endX;
                            startY = endY;
                        }
                    }

                    // 画箭头
                    drawArrow(startX, startY, endX, endY);
                }


                if(TYPE_START_EVENT == this._type) { // 画开始节点
                    this.graphics.lineStyle(1, 0x000000);
                    this.graphics.beginFill(0xffffff);
                    this.graphics.drawCircle(_x + _width / 2, _y + _height / 2, 15);
                    this.graphics.endFill();
                } else if(TYPE_END_EVENT == _type) { // 画结束节点
                    this.graphics.lineStyle(4, 0x000000);
                    this.graphics.beginFill(0xffffff);
                    this.graphics.drawCircle(_x + _width / 2, _y + _height / 2, 15);
                    this.graphics.endFill();
                } else if(TYPE_USER_TASK == _type) { // 画用户任务节点

                } else if(TYPE_PARALLEL_GATEWAY == _type) { // 菱形中画十字
                    this.graphics.lineStyle(4, 0x000000);
                    this.graphics.moveTo(_x + 9, _y + _height/2);
                    this.graphics.lineTo(_x + _width - 9, _y + _height/2); // 画横线
                    this.graphics.moveTo(_x + _width/2, _y + 9);
                    this.graphics.lineTo(_x + _width/2, _y + _height - 9); // 画竖线
                }
            } else {
                this.source = _icon;
            }
            text.htmlText = "<font size='12'>" + this._label + "</font>"
        }

        private function drawArrow(startX:Number, startY:Number, endX:Number, endY:Number):void {
            var angle:int = getAngle(startX, startY, endX, endY);
            var centerX:int = endX -radius * Math.cos(angle *(Math.PI/180)) ;
            var centerY:int= endY + radius * Math.sin(angle *(Math.PI/180)) ;
            var leftX:int=centerX + radius * Math.cos((angle +120) *(Math.PI/180))  ;
            var leftY:int=centerY - radius * Math.sin((angle +120) *(Math.PI/180))  ;

            var rightX:int=centerX + radius * Math.cos((angle +240) *(Math.PI/180))  ;
            var rightY:int=centerY - radius * Math.sin((angle +240) *(Math.PI/180))  ;

            this.graphics.beginFill(0x000000, 1);

            this.graphics.lineStyle(1, 0x000000, 1);

            this.graphics.moveTo(endX, endY);
            this.graphics.lineTo(leftX,leftY);

            this.graphics.lineTo(centerX,centerY);

            this.graphics.lineTo(rightX,rightY);
            this.graphics.lineTo(endX, endY);

            this.graphics.endFill();
        }

        private static function getAngle(startX:Number, startY:Number, endX:Number, endY:Number):int{
            var tmpX:int = endX - startX;
            var tmpY:int = startY - endY;
            return Math.atan2(tmpY, tmpX) * (180/Math.PI);
        }

        // 画菱形
        private static function drawRhombus(graphics:Graphics, x:Number, y:Number, width:Number, height:Number):void {
            graphics.moveTo(x, y + height / 2);
            graphics.lineTo(x + width / 2, y);
            graphics.lineTo(x + width, y + height / 2);
            graphics.lineTo(x + width / 2, y + height);
            graphics.lineTo(x, y + height / 2);
        }

        /**
         * 为图标添加边框
         */
        private function drawBorder():void {
            /*var rect:RectBorder = new RectBorder(2, 0xFF0000, _width, _height, 0x000000, 2, 2);
            this.selected = true; //保存选中状态
            this.addChild(rect);*/
        }

        // 绑定事件
        private function bindEvents():void {
            this.addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
            this.addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
            this.addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler);
        }

        // 触发鼠标按下事件
        private function mouseDownHandler(event:MouseEvent):void {
            var e:IconEvent = new IconEvent(IconEvent.ICON_MOUSE_DOWN);
            e.icon = this;
            glow.target = this;
            glow.end();
            glow.play();

            this.dispatchEvent(e);
        }

        // 触发鼠标弹起事件
        private function mouseUpHandler(event:MouseEvent):void {
            var e:IconEvent = new IconEvent(IconEvent.ICON_MOUSE_UP);
            e.icon = this;
            this.dispatchEvent(e);
        }

        // 触发鼠标移动事件
        private function mouseMoveHandler(event:MouseEvent):void {
            var e:IconEvent = new IconEvent(IconEvent.ICON_MOVE);
            e.icon = this;
            this.dispatchEvent(e);
        }

        // 高亮此节点
        public function highlight():void {
            if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                clearHighlight();
                highlightRectBorder = new IconBorder();
                highlightRectBorder.drawRect(2, 0xff0000, _width, _height, 0, 20, 20, -5.5, -1.5, false);
                this.addChild(highlightRectBorder);
                this.isHighlight = true;
            }
        }

        // 清除高亮
        public function clearHighlight():void {
            if(highlightRectBorder != null && this.contains(highlightRectBorder)) {
                this.removeChild(highlightRectBorder);
                this.isHighlight = false;
                highlightRectBorder = null;
            }
        }

        // 选中
        public function selected():void {
            clearSelected();
            selectedRectBorder = new IconBorder();
            if(TYPE_START_EVENT == this._type) { // 画开始节点
                selectedRectBorder.drawCircle(1, 0x0000ff, _x + _width / 2, _y + _height / 2);
            } else if(TYPE_END_EVENT == _type) { // 画结束节点
                selectedRectBorder.drawCircle(4, 0x0000ff, _x + _width / 2, _y + _height / 2);
            } else if(TYPE_USER_TASK == _type || TYPE_SERVICE_TASK == _type) {
                selectedRectBorder.drawRect(1, 0x0000ff, _width, _height, 0, 20, 20, -5.5, -1.5);
            } else if(TYPE_EXCLUSIVE_GATEWAY == _type || TYPE_PARALLEL_GATEWAY == _type) { // 画菱形
                selectedRectBorder.drawRhombus(1, 0x0000ff, _width, _height, 0, _x,  _y);
            } else if(TYPE_SEQUENCE_FLOW == _type) { // 画线
                selectedRectBorder.drawLine(_di, 1, 0x0000ff);
            }
            this.addChild(selectedRectBorder);
            this.isSelected = true;
        }

        // 清除选中
        public function clearSelected():void {
            if(selectedRectBorder != null && this.contains(selectedRectBorder)) {
                this.removeChild(selectedRectBorder);
                this.isSelected = false;
                selectedRectBorder = null;
            }
        }
    }
}
