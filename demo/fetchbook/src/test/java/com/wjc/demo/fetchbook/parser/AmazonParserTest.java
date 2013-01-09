package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class AmazonParserTest {
    @Test
    public void testParseChinese() {
//                String isbn = "9787544731706"; // 少年Pi的奇幻漂流
        String isbn = "9787504479037"; // 牛刀说货币：货币狼烟
        AmazonParser parser = new AmazonParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("AMAZON"));
        assertThat(prod.getName(), equalTo("牛刀说货币:货币狼烟"));
        assertThat(prod.getPictureURL().toString(), equalTo("http://ec4.images-amazon.com/images/I/518hrslAYgL._SL500_AA300_.jpg"));
        assertThat(prod.getPrice(), equalTo("36.80"));
        assertThat(prod.getAuthor(), equalTo("牛刀"));
        assertThat(prod.getPublishing(), equalTo("中国商业出版社"));
        assertThat(prod.getPublishDate(), equalTo("2012年12月13日"));
        assertThat(prod.getBanci(), equalTo("1"));
        assertThat(prod.getPageNum(), equalTo("218"));
        assertThat(prod.getKaiben(), equalTo("16"));
        assertThat(prod.getIsbn(), equalTo("9787504479037"));
        assertThat(prod.getPack(), equalTo("平装"));
        assertThat(prod.getLanguage(), equalTo("简体中文"));
        assertThat(prod.getSize(), equalTo("23.8 x 16.8 x 2 cm"));
        assertThat(prod.getWeight(), equalTo("422 g"));

        /*assertThat(prod.getWordCount(), equalTo("180000"));
        assertThat(prod.getPrintDate(), equalTo("2012-12-1"));
        assertThat(prod.getPaper(), equalTo("胶版纸"));
        assertThat(prod.getPrintNum(), equalTo("1"));
        assertThat(prod.getSuitNum(), equalTo("0"));
        assertThat(prod.getReaders(), equalTo(null));
        assertThat(prod.getSeriesName(), equalTo(""));
        assertThat(prod.getTranslator(), equalTo(null));
        assertThat(prod.getPainter(), equalTo(null));*/
    }

    @Test
    public void testParseEnglish() {
        String isbn = "9781408703748"; // Steve Jobs - The Exclusive Biography 乔布斯传记-英国版精装
        AmazonParser parser = new AmazonParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("AMAZON"));
        assertThat(prod.getName(), equalTo("Steve Jobs: The Exclusive Biography (史蒂夫•乔布斯传)(英国版)"));
        assertThat(prod.getPictureURL().toString(), equalTo("http://ec4.images-amazon.com/images/I/510O6F6qUJL._SL500_AA300_.jpg"));
        assertThat(prod.getPrice(), equalTo("282.85"));
        assertThat(prod.getAuthor(), equalTo("Walter Isaacson"));
        assertThat(prod.getPublishing(), equalTo("Little, Brown"));
        assertThat(prod.getPublishDate(), equalTo("2011年10月24日"));
        assertThat(prod.getBanci(), equalTo(null));
        assertThat(prod.getPageNum(), equalTo("656"));
        assertThat(prod.getKaiben(), equalTo(null));
        assertThat(prod.getIsbn(), equalTo("9781408703748"));
        assertThat(prod.getPack(), equalTo("精装"));
        assertThat(prod.getLanguage(), equalTo("英语"));
        assertThat(prod.getSize(), equalTo("15.9 x 5.5 x 24 cm"));
        assertThat(prod.getWeight(), equalTo("1 Kg"));
    }
}
