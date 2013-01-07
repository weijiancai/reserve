package com.wjc.demo.fetchbook.impl;

import com.wjc.demo.fetchbook.parser.IParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 京东的商品信息
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class WebProduct360Buy {
    public static final String SEARCH_URL = "http://search.360buy.com/Search?enc=utf-8&book=y&keyword=";
    public static final String UNL_SEARCH = "url_search";

    private IParser parser;
    private String isbn;

    public WebProduct360Buy(IParser parser, String isbn) {
        this.parser = parser;
        this.isbn = isbn;

        parse();
    }

    private void parse() {
        try {
            Document doc = Jsoup.connect(SEARCH_URL + isbn).get();
            Elements elements = doc.select("div.main div.right-extra div#plist div.item");
            if (elements != null) {
                /*for (Element element : elements) {

                }*/
                // 取第一个
                if (elements.size() > 0) {
                    Element element = elements.first();
                    String detailUrl = element.select("div.i-img a").first().attr("href");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    @Override
    public URL getPictureURL() {
        return null;
    }

    @Override
    public String getName() {
        return parser.selectValue(UNL_SEARCH, "");
    }*/
}
