/**
 *
 * @author weijiancai
 */
package as05.com.bjsxt.as3 {
    public class Student {
        private var _name:String = 'zhangsan';

        public function Student() {

        }

        public function get name():String {
            return _name;
        }

        public function set name(value:String):void {
            _name = value;
        }
    }
}
