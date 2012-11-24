/**
 *
 * @author weijiancai
 */
package as04 {
    import flash.display.Sprite;

    public class AS_0400_Functions extends Sprite {
        public function AS_0400_Functions() {
            // 函数变量
            var traceParameter:Function = function(aParam:String):void {
                trace(aParam);
            };
            traceParameter("hello");  // hello

            var traceArray:Array = new Array();
            traceArray[0] = function(aParam:String):void {
                trace(aParam);
            };
            traceArray[0]("hello");

            f();
            f('lisi');

            f2("a", "b", "c", "d");
        }

        // 参数有默认值
        public function f(name:String = 'zhangsan'):void {
            trace(name);
        }

        // 可变参数
        public function f2(...args):void {
            trace(args.length);
        }
    }
}
