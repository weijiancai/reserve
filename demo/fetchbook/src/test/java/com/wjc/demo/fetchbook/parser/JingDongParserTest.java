package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class JingDongParserTest {
    @Test
    public void testParse() throws Exception {
        JingDongParser parser = new JingDongParser("9787544731706");
        IWebProduct prod = parser.parse();
        assertThat(prod.getName(), equalTo("少年Pi（少年派）的奇幻漂流（插图珍藏版）"));
        assertThat(prod.getPictureURL().toString(), equalTo(new URL("http://img10.360buyimg.com/n11/g6/M03/02/15/rBEGF1CkuaAIAAAAAAEwfxp3DX0AAAhNQJXS0YAATCX981.jpg").toString()));
        System.out.println("概述：");
        System.out.println(prod.getHAbstract());
        System.out.println("内容简介：");
        System.out.println(prod.getContent());
        System.out.println("作者简介：");
        System.out.println(prod.getAuthorIntro());
    }
}
