/**
 *
 * @author weijiancai
 */
package as03 {
    import flash.display.Sprite;

    public class AS_0300_Statements extends Sprite {
        public function AS_0300_Statements() {
            var v1:Array = ["a", "b", "c", "d"];
            for(var i:String in v1) {
                trace(i);  // 索引位置，下标值
                trace(v1[i]);
            }

            for each (var propertyValue:String in v1) {
                trace(propertyValue);
            }

            var v2:Object = {id:1, name:'lisi', age:19};
            for(var j in v2) {
                trace(j);  // 属性名
                trace(v2[j]);
            }

            for each (propertyValue in v2) {
                trace(propertyValue);
            }

            trace(v2["name"]);
        }
    }
}
