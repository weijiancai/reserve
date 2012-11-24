/**
 *
 * @author weijiancai
 */
package as07 {
    import flash.display.Sprite;

    public class AS_0700_Array extends Sprite {
        public function AS_0700_Array() {
            var a1:Array = [1, 2, 3];
            var a2:Array = ["a", "b", "c"];
            var a3:Array = new Array();
            trace(a3.push("one"));
            trace(a3.push("two"));
            trace(a3.push("three"));
            trace(a3);
            var a4:Array = new Array(3);
            trace(a4.length);
            trace(a4[0]);
            var a5:Array = new Array("zhangsan", "lisi", "wangwu");
            a5.unshift("zhaoliu"); // 插在最前面
            trace(a5);
            a5.splice(1, 0, "sunqi", "liuba");
            trace(a5);
            a5.splice(1, 2);
            trace(a5);
            a5.pop();
            a5.shift();
            delete a5[0];  // 相当于 a5[0] = undefined
            trace(a5[0]);
            a5.reverse();

            a5.sort();
            a5.sort(Array.CASEINSENSITIVE); // 不区分大小写
            a5.sort(Array.DESCENDING | Array.CASEINSENSITIVE);  // 逆序 并且不区分大小写

            var poets:Array = new Array();
            poets.push({name:"Angelou", born:"1928"});
            poets.push({name:"Blake", born:"1757"});
            poets.push({name:"cummings", born:"1894"});
            poets.push({name:"Dante", born:"1276"});
            poets.push({name:"Wang", born:"701"});
            poets.sortOn("born", Array.NUMERIC);
            trace(poets);
        }
    }
}
