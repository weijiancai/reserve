package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class JingDongParserTest {
    @Test
    public void testParse() throws Exception {
//        String isbn = "9787504479037"; // 牛刀说货币：货币狼烟
        String isbn = " 9780747573906"; // 101 Things To Do Before You Die
        JingDongParser parser = new JingDongParser(isbn);
        IWebProduct prod = parser.parse();
//        assertThat(prod.getName(), equalTo("少年Pi（少年派）的奇幻漂流（插图珍藏版）"));
//        assertThat(prod.getPictureURL().toString(), equalTo(new URL("http://img10.360buyimg.com/n11/g6/M03/02/15/rBEGF1CkuaAIAAAAAAEwfxp3DX0AAAhNQJXS0YAATCX981.jpg").toString()));
        /*System.out.println("书名：");
        System.out.println(prod.getName());
        System.out.println("图片URL：");
        System.out.println(prod.getPictureURL().toString());
        System.out.println("概述：");
        System.out.println(prod.getHAbstract());
        System.out.println("内容简介：");
        System.out.println(prod.getContent());
        System.out.println("作者简介：");
        System.out.println(prod.getAuthorIntro());
        System.out.println("目录：");
        System.out.println(prod.getCatalog());
        System.out.println("前言：");*/
        System.out.println(prod);
    }

    @Test
    public void testParseChinese() {
        String isbn = "9787504479037"; // 牛刀说货币：货币狼烟
        JingDongParser parser = new JingDongParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("JING_DONG"));
        assertThat(prod.getName(), equalTo("牛刀说货币：货币狼烟"));
        assertThat(prod.getPictureURL().toString(), equalTo("http://img10.360buyimg.com/n11/g7/M03/0E/0C/rBEHZVCkY6YIAAAAAAD9h0RbUkwAACz9wPKNrsAAP2f990.jpg"));
        assertThat(prod.getAuthor(), equalTo("牛刀"));
        assertThat(prod.getPublishing(), equalTo("中国商业出版社"));
        assertThat(prod.getBanci(), equalTo("1"));
        assertThat(prod.getIsbn(), equalTo("9787504479037"));
        assertThat(prod.getPack(), equalTo("平装"));
        assertThat(prod.getPageNum(), equalTo("240"));
        assertThat(prod.getPrice(), equalTo("36.80"));
        assertThat(prod.getPaper(), equalTo("胶版纸"));
        assertThat(prod.getLanguage(), equalTo("中文"));
        assertThat(prod.getKaiben(), equalTo("16开"));
        assertThat(prod.getPrintDate(), equalTo("2012-12-01"));
        assertThat(prod.getPrintNum(), equalTo(null));
        assertThat(prod.getSuitNum(), equalTo("0"));
        assertThat(prod.getReaders(), equalTo(null));
        assertThat(prod.getSeriesName(), equalTo(""));
        assertThat(prod.getTranslator(), equalTo(null));
        assertThat(prod.getPainter(), equalTo(null));
    }

    @Test
    public void testParseEnglish() {
        String isbn = "9780747573906"; // 101 Things To Do Before You Die
        JingDongParser parser = new JingDongParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("JING_DONG"));
        assertThat(prod.getName(), equalTo("101 Things To Do Before You Die"));
        assertThat(prod.getCnName(), equalTo("死前需要做的101件事情"));
        assertThat(prod.getPictureURL().toString(), equalTo("http://img10.360buyimg.com/n11/12989/ee270950-bb3d-471a-92ca-c89945a629cd.jpg"));
        assertThat(prod.getAuthor(), equalTo("Richard Horne（理查德・霍恩）"));
        assertThat(prod.getPublishing(), equalTo("Bloomsbury Publishing PLC"));
        assertThat(prod.getBanci(), equalTo(""));
        assertThat(prod.getIsbn(), equalTo("9780747573906"));
        assertThat(prod.getPack(), equalTo("平装"));
        assertThat(prod.getPageNum(), equalTo("224"));
        assertThat(prod.getPrice(), equalTo("107.40"));
        assertThat(prod.getSize(), equalTo("17.27x11.18x2.03cm"));
        assertThat(prod.getWeight(), equalTo("0.3kg"));
        assertThat(prod.getPaper(), equalTo("胶版纸"));
        assertThat(prod.getLanguage(), equalTo("英文"));
    }
}
