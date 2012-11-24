/**
 *
 * @author weijiancai
 */
package as06 {
    import flash.display.Sprite;

    public class AS_0600_String extends Sprite {
        public function AS_0600_String() {
            var s:String = "www.bjsxt.com";
            for(var i:int = 0; i < s.length; i++) {
                trace(s.charAt(i), s.charCodeAt(i));
            }

            trace(s.substring(0, 5)); // substr splice
            trace(s.concat("/index"));
            trace(s.toUpperCase());
        }
    }
}
