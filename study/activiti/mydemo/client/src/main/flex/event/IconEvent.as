/**
 * Created with IntelliJ IDEA.
 * User: WangXinghao
 * Date: 12-12-5
 * Time: 上午9:39
 * To change this template use File | Settings | File Templates.
 */
package event {
    import flash.events.Event;

    public class IconEvent extends Event {
        public static var ICON_MOUSE_DOWN:String = "icon_mouse_down";
        public static var ICON_MOUSE_UP:String = "icon_mouse_up";
        public static var ICON_MOVE:String = "icon_move";

        public var icon:ProcessIcon;

        public function IconEvent(type:String) {
            super(type);
        }
    }
}
