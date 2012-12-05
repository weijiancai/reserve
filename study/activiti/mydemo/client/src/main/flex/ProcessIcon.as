/**
 *
 * @author weijiancai
 */
package {
    import flash.text.TextField;

    import mx.controls.Image;

    public class ProcessIcon extends Image {
        // 图像唯一标识符
        private var _id:String;
        // 图标类型
        private var _type:int;
        // 图标上的文字说明
        private var _label:String;
        // 备注
        private var _memo:String;
        // 图标是否被选中
        private var _isSelected:Boolean;
        // 图像数据
        private var _icon:Object;

        private var text:TextField = new TextField();

        public function ProcessIcon() {
        }


        /*public function get id():String {
            return _id;
        }

        public function set id(value:String):void {
            _id = value;
        }*/

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

        public function get isSelected():Boolean {
            return _isSelected;
        }

        public function set isSelected(value:Boolean):void {
            _isSelected = value;
        }

        public function get icon():Object {
            return _icon;
        }

        public function set icon(value:Object):void {
            _icon = value;
        }
    }
}
