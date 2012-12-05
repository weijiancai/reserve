/**
 *
 * @author weijiancai
 */
package {
    import event.IconEvent;

    import flash.display.Bitmap;

    import flash.display.BitmapData;

    import flash.events.MouseEvent;
    import flash.text.TextField;
    import flash.text.TextFieldAutoSize;

    import mx.controls.Alert;
    import mx.controls.Button;

    import mx.controls.Image;
    import mx.controls.Label;

    [Event(name = IconEvent.ICON_MOUSE_DOWN, type = "event.IconEvent")]
    [Event(name = IconEvent.ICON_MOUSE_UP, type = "event.IconEvent")]
    [Event(name = IconEvent.ICON_MOVE, type = "event.IconEvent")]

    public class ProcessIcon extends Image {
        [Bindable]
        [Embed(source = "images/user.png")]
        public static var userTaskPng:Class;  // 用户任务

        [Bindable]
        [Embed(source = "images/service.png")]
        public var serviceTaskPng:Class;  // 服务任务

        // 图像唯一标识符
//        private var _id:String;
        // 名称
        private var _key:String;
        // 图标类型
        private var _type:String;
        // 图标上的文字说明
        private var _label:String;
        // 备注
        private var _memo:String;
        // 图标是否被选中
        private var _selected:Boolean;
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

        private var text:TextField = new TextField();

        public function ProcessIcon(type:String = "", key:String = "", label:String = "", x:Number = 0, y:Number = 0, width:Number = 0, height:Number = 0, icon:Object = null, selected:Boolean = false, memo:String = "") {
            super();
            this._icon = icon;
            this._type = type;
            this._key = key;
            this._label = label;
            this._selected = selected;
            this._memo = memo;

            this._x = x;
            this._y = y;
            this._width = width;
            this._height = height;

            this.graphics.clear();
            //填充背景
            /*this.graphics.beginFill(0x568954);
            this.graphics.drawRect(0, 0, 50, 20);
            this.graphics.endFill();*/

            text.autoSize = TextFieldAutoSize.LEFT;

            this.draw();
            this.bindEvents();
        }


        /*public function get id():String {
         return _id;
         }

         public function set id(value:String):void {
         _id = value;
         }*/

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


        public function get selected():Boolean {
            return _selected;
        }

        public function set selected(value:Boolean):void {
            _selected = value;
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
                if(SysConst.TYPE_START_EVENT == this._type) { // 画开始节点
                    this.graphics.lineStyle(1, 0x000000);
                    this.graphics.drawCircle(_x + _width / 2, _y + _height / 2, 15);

                    text.x = _x - 3;
                    text.y = _y + 30 + 3;
                    this.addChild(text);
                } else if(SysConst.TYPE_END_EVENT == _type) { // 画结束节点
                    this.graphics.lineStyle(4, 0x000000);
                    this.graphics.drawCircle(_x + _width / 2, _y + _height / 2, 15);

                    text.x = _x - 3;
                    text.y = _y + 30 + 3;
                    this.addChild(text);
                } else if(SysConst.TYPE_USER_TASK == _type) { // 画用户任务节点
                    /*this.x = _x;
                    this.y = _y;
                    this.source = userTaskPng;*/

                    this.graphics.lineStyle(0,0,1, true);
                    this.graphics.drawRoundRect(_x + 0.5,  _y + 0.5,  _width, _height, 20, 20);  // + 0.5抗锯齿

                    var img:Image = new Image();
                    img.source = userTaskPng;
                    var myBitmapData:BitmapData = new BitmapData(16, 16,true,0x00000000);//true表示背景透明
                    myBitmapData.draw(img);

                    var bmp:Bitmap = new Bitmap(myBitmapData);
                    bmp.x = _x;
                    bmp.y = _y;

                }
            } else {
                this.source = _icon;
            }
            text.htmlText = "<b>" + this._label + "</b>"
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
    }
}
