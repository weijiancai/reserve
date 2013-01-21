package com.wjc.demo.fetchbook.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class UtilStringTest {
    @Test
    public void testTrim() throws Exception {
        String str = "牛刀说货币：货币狼烟（股市不行，楼市不行，2013年最好的理财方法是外汇理财）  ";
        String exptected = "牛刀说货币：货币狼烟（股市不行，楼市不行，2013年最好的理财方法是外汇理财）";
        assertThat(UtilString.trim(str), equalTo(exptected));
    }

    @Test
    public void test() {
    }
}
