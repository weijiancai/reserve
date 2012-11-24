/**
 *
 * @author weijiancai
 */
package as05 {
    import as05.com.bjsxt.as3.IFlyable;
    import as05.com.bjsxt.as3.Student;
    import as05.com.bjsxt.as3.T;
    import as05.com.bjsxt.as3.Teacher;

    import flash.display.Sprite;

    public class AS_0500_OO extends Sprite {
        public function AS_0500_OO() {
            var s:Student = new Student();
            trace(s.name);

            var t:Teacher = new Teacher();
            // 动态添加函数
            t.f = function():void {
                trace("f");
            };
            t.f();
            // 删除函数
            delete t.f;
            try {
                t.f();
            } catch (e:Error) {
                trace(e.message);
            }


            // 动态添加变量
            t.name = "lisi";
            trace(t.name);
            // 删除变量
            delete t.name;
            trace(t.name);

            // 使用接口
            var flyable:IFlyable = new T();
            flyable.fly();
        }
    }
}
