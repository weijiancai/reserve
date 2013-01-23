package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.junit.Assert.assertThat;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class AmazonParserTest {
    // 少年Pi的奇幻漂流
    @Test public void test9787544731706() throws MalformedURLException {
        String isbn = "9787544731706";
        AmazonParser parser = new AmazonParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("AMAZON"));
        assertThat(prod.getName(), equalTo("少年Pi的奇幻漂流(插图珍藏版)"));
        assertThat(prod.getAuthor(), equalTo("扬•马特尔 (Yann Martel)"));
        assertThat(prod.getPictureURLs().length, equalTo(2));
        assertThat(prod.getPictureURLs(), hasItemInArray(new URL("http://ec4.images-amazon.com/images/I/510eoZh-ltL._AA115_.jpg"))); // 160 * 160
        assertThat(prod.getPictureURLs(), hasItemInArray(new URL("http://ec4.images-amazon.com/images/I/510eoZh-ltL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU28_.jpg"))); // 280 * 280
        assertThat(prod.getPrice(), equalTo("35.00"));
        assertThat(prod.getPublishing(), equalTo("译林出版社"));
        assertThat(prod.getPublishDate(), equalTo("2012年11月29日"));
        assertThat(prod.getBanci(), equalTo("1"));
        assertThat(prod.getPageNum(), equalTo("324"));
        assertThat(prod.getKaiben(), equalTo("16"));
        assertThat(prod.getIsbn(), equalTo("9787544731706"));
        assertThat(prod.getLanguage(), equalTo("简体中文"));
        assertThat(prod.getSize(), equalTo("21.4 x 15 x 2.2 cm"));
        assertThat(prod.getWeight(), equalTo("481"));
        assertThat(prod.getLength(), equalTo("21.4"));
        assertThat(prod.getWidth(), equalTo("15"));
        assertThat(prod.getDeep(), equalTo("2.2"));
    }

    // 牛刀说货币：货币狼烟
    @Test public void test9787504479037() throws MalformedURLException {
        String isbn = "9787504479037";
        AmazonParser parser = new AmazonParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("AMAZON"));
        assertThat(prod.getName(), equalTo("牛刀说货币:货币狼烟"));
        assertThat(prod.getPictureURLs().length, equalTo(2));
        assertThat(prod.getPictureURLs(), hasItemInArray(new URL("http://ec4.images-amazon.com/images/I/518hrslAYgL._AA115_.jpg"))); // 160 * 160
        assertThat(prod.getPictureURLs(), hasItemInArray(new URL("http://ec4.images-amazon.com/images/I/518hrslAYgL._SL500_AA300_.jpg"))); // 280 * 280
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
        assertThat(prod.getWeight(), equalTo("422"));
        assertThat(prod.getLength(), equalTo("23.8"));
        assertThat(prod.getWidth(), equalTo("16.8"));
        assertThat(prod.getDeep(), equalTo("2"));
    }

    // 食品雕刻5:瓜雕和虫鱼兽雕刻
    @Test public void test6925002300023() throws MalformedURLException {
        String isbn = "6925002300023";
        AmazonParser parser = new AmazonParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("AMAZON"));
        assertThat(prod.getName(), equalTo("食品雕刻5:瓜雕和虫鱼兽雕刻(1VCD)"));
        assertThat(prod.getPictureURLs().length, equalTo(2));
        assertThat(prod.getPictureURLs(), hasItemInArray(new URL("http://ec4.images-amazon.com/images/I/61UKOJcv9%2BL._AA115_.jpg"))); // 160 * 160
        assertThat(prod.getPictureURLs(), hasItemInArray(new URL("http://ec4.images-amazon.com/images/I/61UKOJcv9%2BL._SL500_AA300_.jpg"))); // 280 * 280
        assertThat(prod.getAuthor(), equalTo("毛景海（主讲）"));
        assertThat(prod.getPublishing(), equalTo("中国人民解放军音像出版社"));
        assertThat(prod.getIsbn(), equalTo("6925002300023"));
        assertThat(prod.getSize(), equalTo("14 x 12.2 x 1 cm"));
        assertThat(prod.getWeight(), equalTo("59"));
        assertThat(prod.getLength(), equalTo("14"));
        assertThat(prod.getWidth(), equalTo("12.2"));
        assertThat(prod.getDeep(), equalTo("1"));
    }

    // Steve Jobs - The Exclusive Biography 乔布斯传记-英国版精装
    @Test public void test9781408703748() {
        String isbn = "9781408703748";
        AmazonParser parser = new AmazonParser(isbn);
        IWebProduct prod = parser.parse();
        System.out.println(prod);

        assertThat(prod.getSourceSite(), equalTo("AMAZON"));
        assertThat(prod.getName(), equalTo("Steve Jobs: The Exclusive Biography (史蒂夫•乔布斯传)(英国版)"));
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
        assertThat(prod.getWeight(), equalTo("1"));
    }
}
