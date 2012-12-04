/**
 *
 * @author weijiancai
 */
package {
    import flash.text.TextField;

    import mx.controls.Image;

    public class ProcessIcon extends Image {
        // 图像唯一标识符
//        private var _id:String;
        // 名称
        private var _key:String;
        // 图标类型
        private var _type:int;
        // 图标上的文字说明
        private var _label:String;
        // 备注
        private var _memo:String;
        // 图标是否被选中
        private var _selected:Boolean;
        // 图像数据
        private var _icon:Object;

        private var text:TextField = new TextField();

        public function ProcessIcon(icon:Object, type:int = 0, key:String = "", label:String = "", selected:Boolean = false, memo:String = "") {
            super();
            this._icon = icon;
            this._type = type;
            this._key = key;
            this._label = label;
            this._selected = selected;
            this._memo = memo;

            this.graphics.clear();
            //填充背景
            this.graphics.beginFill(0x568954);
            this.graphics.drawRect(0, 0, 50, 20);
            this.graphics.endFill();
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

        public function get type():int {
            return _type;
        }

        public function set type(value:int):void {
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
    }
}
