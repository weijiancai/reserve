package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import com.wjc.demo.fetchbook.WebProductImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class JingDongParser implements IProductParser {
    public static final String SEARCH_URL = "http://search.360buy.com/Search?enc=utf-8&book=y&keyword=";

    private String isbn;

    public JingDongParser(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public IWebProduct parse() {
        WebProductImpl prod = new WebProductImpl();
        try {
            // 打开搜索结果页面
            Document doc = Jsoup.connect(SEARCH_URL + isbn).get();
            Elements elements = doc.select("div.main div.right-extra div#plist div.item");
            if (elements != null) {
                /*for (Element element : elements) {

                }*/
                // 取第一个
                if (elements.size() > 0) {
                    Element element = elements.first();
                    // 打开详细页面
                    String detailUrl = element.select("div.i-img a").first().attr("href");

                    Document detailDoc = Jsoup.connect(detailUrl).get();
                    // 取书名
                    prod.setName(detailDoc.select("div.main div.right-extra div#name h1").first().ownText());
                    // 取图片
                    prod.setPicture(new URL(detailDoc.select("div.main div.right-extra div#preview div#spec-n1 img").first().attr("src")));
                    // 取编辑推荐
                    prod.setHAbstract(detailDoc.select("div.main div.right-extra div#recommend-editor div.mc div.con").first().text());
                    // 取内容简介
                    prod.setContent(detailDoc.select("div.main div.right-extra > div.m1").get(1).select("div.mc div.con").text());
                    // 取作者简介
                    prod.setAuthorIntro(detailDoc.select("div.main div.right-extra > div.m1").get(2).select("div.mc div.con").text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prod;
    }
}
