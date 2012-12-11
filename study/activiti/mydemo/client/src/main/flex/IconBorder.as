/**
 *
 * @author weijiancai
 */
package {
    import flash.display.Shape;
    import flash.net.sendToURL;

    public class IconBorder extends Shape {
        public static const TYPE_CIRCLE:int = 0;
        public static const TYPE_RECTANGLE:int = 1;

        private var _thickness:Number;	// 线条宽度
        private var _color:uint;		// 线条颜色
        private var _width:uint;		// 矩形宽
        private var _height:uint;		// 矩形高
        private var _fillColor:uint;	// 填充 颜色
        private var _eclipseWidth:uint;	// 矩形圆角 宽
        private var _eclipseHeight:uint;	// 矩形圆角 高
        private var _x:int; // 矩形的x坐标
        private var _y:int; // 矩形的y坐标

        public function get thickness():Number {
            return _thickness;
        }

        public function set thickness(value:Number):void {
            _thickness = value;
        }

        public function get color():uint {
            return _color;
        }

        public function set color(value:uint):void {
            _color = value;
        }

        public function get rectWidth():uint {
            return _width;
        }

        public function set rectWidth(value:uint):void {
            _width = value;
        }

        public function get rectHeight():uint {
            return _height;
        }

        public function set rectHeight(value:uint):void {
            _height = value;
        }

        public function get fillColor():uint {
            return _fillColor;
        }

        public function set fillColor(value:uint):void {
            _fillColor = value;
        }

        public function get eclipseWidth():uint {
            return _eclipseWidth;
        }

        public function set eclipseWidth(value:uint):void {
            _eclipseWidth = value;
        }

        public function get eclipseHeight():uint {
            return _eclipseHeight;
        }

        public function set eclipseHeight(value:uint):void {
            _eclipseHeight = value;
        }

        public function IconBorder() {
            super();
        }

        public function drawRect(thickness:Number = 0, color:uint = 0, width:Number = 0, height:Number = 0, fillColor:uint = 0, eclipseWidth:Number = 0, eclipseHeight:Number = 0, x:Number = 0, y:Number = 0, isFill:Boolean = true):void {
            this.graphics.lineStyle(thickness, color, 1, true);
            if(isFill) {
                this.graphics.beginFill(fillColor, 0.4);
            }
            this.graphics.drawRoundRect(x,  y,  width, height, eclipseWidth, eclipseHeight);  // + 0.5抗锯齿
            if(isFill) {
                this.graphics.endFill();
            }
        }

        public function drawCircle(thickness:Number = 0, color:uint = 0, x:Number = 0, y:Number = 0, fillColor:uint = 0, radius:Number = 15):void {
            this.graphics.lineStyle(thickness, color, 1, true);
            this.graphics.beginFill(fillColor, 0.4);
            this.graphics.drawCircle(x, y, radius);
            this.graphics.endFill();
        }

        public function drawRhombus(thickness:Number = 0, color:uint = 0, width:Number = 0, height:Number = 0, fillColor:uint = 0, x:Number = 0, y:Number = 0):void {
            this.graphics.lineStyle(thickness, color, 1, true);
            this.graphics.beginFill(fillColor, 0.4);
            graphics.moveTo(x, y + height / 2);
            graphics.lineTo(x + width / 2, y);
            graphics.lineTo(x + width, y + height / 2);
            graphics.lineTo(x + width / 2, y + height);
            graphics.lineTo(x, y + height / 2);
            this.graphics.endFill();
        }

        public function drawLine(di:XML, thickness:Number = 0, color:uint = 0):void {
            this.graphics.lineStyle(thickness, color, 1, true);
            var waypointList:XMLList = di.children();
            var startX:Number = 0, startY:Number = 0, endX:Number = 0, endY:Number = 0;
            for (var j:int = 0; j < waypointList.length(); j++) {
                if (endX > 0 && endY > 0) {
                    this.graphics.moveTo(endX, endY);
                    this.graphics.lineTo(Number(waypointList[j].@x), Number(waypointList[j].@y));
                }

                endX = Number(waypointList[j].@x);
                endY = Number(waypointList[j].@y);

                if(j == waypointList.length() - 2) { // 记录最后一个开始点的位置
                    startX = endX;
                    startY = endY;
                }
            }
        }

        private function draw():void {

        }
    }
}
