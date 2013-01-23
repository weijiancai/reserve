package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class DouBanParserTest {
    // 少年Pi的奇幻漂流
    @Test public void test9787544731706() throws MalformedURLException {
        String isbn = "9787544731706";
        DouBanParser parser = new DouBanParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("DOU_BAN"));
        assertThat(prod.getName(), equalTo("少年Pi的奇幻漂流"));
        assertThat(prod.getAuthor(), equalTo("[加拿大] 扬·马特尔"));
        assertThat(prod.getTranslator(), equalTo("姚媛"));
        assertThat(prod.getPrice(), equalTo("35.00"));
        assertThat(prod.getPublishing(), equalTo("译林出版社"));
        assertThat(prod.getPublishDate(), equalTo("2012-11-28"));
        assertThat(prod.getPageNum(), equalTo("324"));
        assertThat(prod.getIsbn(), equalTo("9787544731706"));
        assertThat(prod.getPack(), equalTo("平装"));
    }

    // 牛刀说货币：货币狼烟
    @Test public void test9787504479037() throws MalformedURLException {
        String isbn = "9787504479037";
        DouBanParser parser = new DouBanParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("DOU_BAN"));
        assertThat(prod.getName(), equalTo("牛刀说货币"));
        assertThat(prod.getPrice(), equalTo("36.80"));
        assertThat(prod.getAuthor(), equalTo("牛刀"));
        assertThat(prod.getPublishing(), equalTo("中国商业出版社"));
        assertThat(prod.getPublishDate(), equalTo("2012-12-5"));
        assertThat(prod.getPageNum(), equalTo("232"));
        assertThat(prod.getIsbn(), equalTo("9787504479037"));
    }

    // Steve Jobs - The Exclusive Biography 乔布斯传记-英国版精装
    @Test public void test9781408703748() {
        String isbn = "9781408703748";
        DouBanParser parser = new DouBanParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("DOU_BAN"));
        assertThat(prod.getName(), equalTo("Steve Jobs"));
        assertThat(prod.getPrice(), equalTo("GBP 25.00"));
        assertThat(prod.getAuthor(), equalTo("Walter Isaacson"));
        assertThat(prod.getPublishing(), equalTo("Little, Brown"));
        assertThat(prod.getPublishDate(), equalTo("2011-11"));
        assertThat(prod.getPageNum(), equalTo("630"));
        assertThat(prod.getIsbn(), equalTo("9781408703748"));
        assertThat(prod.getPack(), equalTo("Hardcover"));
    }
}
