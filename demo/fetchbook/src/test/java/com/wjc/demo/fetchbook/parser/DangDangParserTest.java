package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class DangDangParserTest {
    // 少年Pi的奇幻漂流
    @Test public void test9787544731706() throws MalformedURLException {
        String isbn = "9787544731706";
        DangDangParser parser = new DangDangParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("DANG_DANG"));
        assertThat(prod.getName(), equalTo("少年Pi的奇幻漂流（插图珍藏版）(不凡历程，不枉此生！奥巴马、李安挚爱的勇气之书，同名电影全球热映。！）"));
        assertThat(prod.getAuthor(), equalTo("扬・马特尔"));
        assertThat(prod.getPainter(), equalTo("（克罗）托亚纳克"));
        assertThat(prod.getTranslator(), equalTo("姚媛"));
        assertThat(prod.getPrice(), equalTo("35.00"));
        assertThat(prod.getPublishing(), equalTo("译林出版社"));
        assertThat(prod.getPublishDate(), equalTo("2012-11-1"));
        assertThat(prod.getPrintDate(), equalTo("2012-11-1"));
        assertThat(prod.getBanci(), equalTo("1"));
        assertThat(prod.getKaiben(), equalTo("小16开"));
        assertThat(prod.getIsbn(), equalTo("9787544731706"));
        assertThat(prod.getPaper(), equalTo("胶版纸"));
        assertThat(prod.getPack(), equalTo("平装"));
    }

    // 牛刀说货币：货币狼烟
    @Test public void test9787504479037() throws MalformedURLException {
        String isbn = "9787504479037";
        DangDangParser parser = new DangDangParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("DANG_DANG"));
        assertThat(prod.getName(), equalTo("牛刀说货币：货币狼烟（股市不行，楼市不行，2013年最好的理财方法是外汇理财）"));
        assertThat(prod.getPrice(), equalTo("36.80"));
        assertThat(prod.getAuthor(), equalTo("牛刀"));
        assertThat(prod.getPublishing(), equalTo("中国商业出版社"));
        assertThat(prod.getPublishDate(), equalTo("2012-12-1"));
        assertThat(prod.getBanci(), equalTo("1"));
        assertThat(prod.getPageNum(), equalTo("218"));
        assertThat(prod.getKaiben(), equalTo("16开"));
        assertThat(prod.getIsbn(), equalTo("9787504479037"));
        assertThat(prod.getPack(), equalTo("平装"));
        assertThat(prod.getPrintNum(), equalTo("1"));
        assertThat(prod.getPaper(), equalTo("胶版纸"));
        assertThat(prod.getWordCount(), equalTo("180000"));
    }

    // Steve Jobs - The Exclusive Biography 乔布斯传记-英国版精装
    @Test public void test9781408703748() {
        String isbn = "9781408703748";
        DangDangParser parser = new DangDangParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("DANG_DANG"));
        assertThat(prod.getName(), equalTo("Steve Jobs - The Exclusive Biography 乔布斯传记-英国版精装 ISBN=9781408703748"));
        assertThat(prod.getPrice(), equalTo("262.00"));
        assertThat(prod.getAuthor(), equalTo("Walter Isaacson"));
        assertThat(prod.getPublishing(), equalTo("Little, Brown UK"));
        assertThat(prod.getPublishDate(), equalTo("2011-11-21"));
        assertThat(prod.getBanci(), equalTo("1"));
        assertThat(prod.getPageNum(), equalTo("630"));
        assertThat(prod.getWordCount(), equalTo(""));
        assertThat(prod.getPrintDate(), equalTo("2011-11-21"));
        assertThat(prod.getKaiben(), equalTo("大32开"));
        assertThat(prod.getPaper(), equalTo("胶版纸"));
        assertThat(prod.getPrintNum(), equalTo("1"));
        assertThat(prod.getIsbn(), equalTo("9781408703748"));
        assertThat(prod.getPack(), equalTo("精装"));
    }

    @Test
    public void test() {
        System.out.println("\uff65");
    }
}
