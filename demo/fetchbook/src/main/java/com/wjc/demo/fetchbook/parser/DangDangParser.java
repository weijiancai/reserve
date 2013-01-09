package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import com.wjc.demo.fetchbook.SiteName;
import com.wjc.demo.fetchbook.WebProductImpl;
import com.wjc.demo.fetchbook.util.UtilString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * 当当解析器
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class DangDangParser implements IProductParser {
    public static final String SEARCH_URL = "http://searchb.dangdang.com/?key=";
    public static final String SUB_DETAIL_URL = "http://product.dangdang.com/detail/main.php?type=all&product_id=";
    private static final int TIME_OUT = 2 * 1000; // 10秒超时

    private String isbn;

    public DangDangParser(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public IWebProduct parse() {
        WebProductImpl prod = new WebProductImpl();
        prod.setSourceSite(SiteName.DANG_DANG.name());
        try {
            // 打开搜索结果页面
            Document doc = Jsoup.connect(SEARCH_URL + isbn).timeout(TIME_OUT).get();
            Elements elements = doc.select("div.list_wrap div.list_main div.resultlist > ul > li");
            if (elements != null) {
                /*for (Element element : elements) {

                }*/
                // 取第一个
                if (elements.size() > 0) {
                    Element element = elements.first();
                    Elements mElements;
                    // 打开详细页面
                    String detailUrl = element.select("div.pic a").first().attr("href");

                    Document detailDoc = Jsoup.connect(detailUrl)
                            .timeout(TIME_OUT)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .get();
                    // 取书名
                    prod.setName(UtilString.trim(detailDoc.select("div.main > div.head h1").first().ownText()));
                    // 取图片
                    prod.setPicture(new URL(detailDoc.select("div.main > div.show > div.show_pic > div.big > a > img").first().attr("wsrc")));
                    // 取定价
                    prod.setPrice(detailDoc.select("div.main > div.show > div.show_info > div.sale > p > .m_price").first().text().replace("¥", ""));
                    // 取作者
                    String authorStr = detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(0).text().replace("作 者：", "");
                    for (String str : authorStr.split("，")) {
                        String value = str.substring(0, str.length() - 1);
                        if (str.endsWith("著")) {
                            prod.setAuthor(UtilString.trim(value));
                        } else if (str.endsWith("图")) {
                            prod.setPainter(UtilString.trim(value));
                        } else if (str.endsWith("译")) {
                            prod.setTranslator(UtilString.trim(value));
                        }
                    }

                    // 取出版社
                    prod.setPublishing(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(1).select("a").text());

                    // 取出版时间
                    prod.setPublishDate(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(2).text().replace("出版时间：", ""));

                    // 取版次
                    prod.setBanci(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(3).select("> span").get(0).ownText());
                    // 取页数
                    prod.setPageNum(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(3).select("> span").get(1).ownText());
                    // 取字数
                    prod.setWordCount(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(3).select("> span").get(2).ownText());

                    // 取印刷时间
                    prod.setPrintDate(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(4).select("> span").get(0).ownText().replace("印刷时间：", ""));
                    // 取开本
                    prod.setKaiben(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(4).select("> span").get(1).ownText());
                    // 取纸张
                    prod.setPaper(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(4).select("> span").get(2).ownText());

                    // 取印次
                    prod.setPrintNum(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(5).select("> span").get(0).ownText());
                    // 取ISBN
                    prod.setIsbn(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(5).select("> span").get(1).ownText());
                    // 取包装
                    prod.setPack(detailDoc.select("div.main > div.show > div.show_info > ul.intro > li").get(5).select("> span").get(2).ownText());

                    // 取淘宝product_id
                    String productId = detailDoc.select("body > span#pid_span").first().attr("product_id");
                    // 取编辑推荐
                    Document subDetailDoc = Jsoup.connect(SUB_DETAIL_URL + productId).timeout(TIME_OUT).get();
                    mElements = subDetailDoc.select("div#detail_all > div#abstract > div.descrip > textarea");
                    if (mElements.size() > 0) {
                        prod.setHAbstract(mElements.first().text());
                    }
                    // 取内容推荐
                    mElements = subDetailDoc.select("div#detail_all > div#content > div.descrip > textarea");
                    if (mElements.size() > 0) {
                        prod.setContent(mElements.first().text());
                    }
                    // 取作者简介
                    mElements = subDetailDoc.select("div#detail_all > div#authorintro > div.descrip > textarea");
                    if (mElements.size() > 0) {
                        prod.setAuthorIntro(mElements.first().text());
                    }
                    // 取目录
                    mElements = subDetailDoc.select("div#detail_all > div#catalog > div.descrip > textarea");
                    if (mElements.size() > 0) {
                        prod.setCatalog(mElements.first().text());
                    }
                    // 取媒体评论
                    mElements = subDetailDoc.select("div#detail_all > div#mediafeedback > div.descrip > textarea");
                    if (mElements.size() > 0) {
                        prod.setMediaFeedback(mElements.first().text());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prod;
    }
}
