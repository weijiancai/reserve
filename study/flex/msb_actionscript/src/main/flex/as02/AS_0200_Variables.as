/**
 *
 * @author weijiancai
 */
package as02 {
    import flash.display.Sprite;

    public class AS_0200_Variables extends Sprite {
        public function AS_0200_Variables() {
            var v1:int = 9;
            var v2:uint = 10;
            var v3:Boolean = true;
            var v4:Number = 4.5;
            var v5:String = "HelloWorld";
            var v6:String = 'HelloWorld Again';
            var v7:* = 3;
            trace(v7);
            var v8;
            trace("v8 = ", v8);
            var v9:String;
            trace(v9);
            var v10:Array = [2, 3, 4, 5];
            var v11:Object = {id:3, name:"zhangsan", age:18};
            trace(v11.name);
        }
    }
}
