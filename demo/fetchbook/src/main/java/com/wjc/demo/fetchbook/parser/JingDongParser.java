package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import com.wjc.demo.fetchbook.SiteName;
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
        prod.setSourceSite(SiteName.JING_DONG.name());
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
                    Elements mElements;
                    // 打开详细页面
                    String detailUrl = element.select("div.i-img a").first().attr("href");

                    Document detailDoc = Jsoup.connect(detailUrl).get();
                    // 取书名
                    prod.setName(detailDoc.select("div.main div.right-extra div#name h1").first().ownText());
                    // 取图片
                    prod.setPicture(new URL(detailDoc.select("div.main div.right-extra div#preview div#spec-n1 img").first().attr("src")));
                    // 取编辑推荐
                    mElements = detailDoc.select("div.main div.right-extra div#recommend-editor div.mc div.con");
                    if (mElements.size() > 0) {
                        prod.setHAbstract(mElements.first().text());
                    }

                    mElements = detailDoc.select("div.main div.right-extra > div.m1 div.mt h2");
                    for (Element aElement : mElements) {
                        String text = aElement.ownText();
                        if (text != null) {
                            text = text.trim();
                            String value = aElement.parent().parent().select("div.mc div.con").text();
                            if ("内容简介".equals(text)) { // 取内容简介
                                prod.setContent(value);
                            } else if ("作者简介".equals(text)) { // 取作者简介
                                prod.setAuthorIntro(value);
                            } else if ("目录".equals(text)) { // 取目录
                                prod.setCatalog(value);
                            } else if ("前言".equals(text)) { // 取前言
                                prod.setPrologue(value);
                            } else if ("精彩书摘".equals(text)) { // 取精彩书摘
                                prod.setExtract(value);
                            }
                        }
                    }

                    // 取定价
                    prod.setPrice(detailDoc.select("div.main div.right-extra ul#book-price li").first().text().replace("定", "").replace("价", "").replace("：", "").replace("￥", "").replace(" ", "").trim());

                    // 取summary-english
                    mElements = detailDoc.select("div.main div.right-extra > ul#summary-english");
                    Element summaryElements;
                    String span;
                    if (mElements != null && mElements.size() > 0) {
                        summaryElements = mElements.first();
                        for (Element aElement : summaryElements.select("li")) {
                            span = aElement.getElementsByTag("span").text();
                            if ("作　　者/Author:".equals(span)) {
                                Elements authorElements = aElement.getElementsByTag("a");
                                if (authorElements.size() > 0) {
                                    prod.setAuthor(authorElements.get(0).text());
                                }
                                if (authorElements.size() > 1) {
                                    prod.setTranslator(authorElements.get(1).text());
                                }
                                if (authorElements.size() > 2) {
                                    prod.setPainter(authorElements.get(2).text());
                                }
                            } else if ("中文书名/Chinese Title:".equals(span)) {
                                prod.setCnName(aElement.ownText());
                            } else if ("出  版  社/Publisher:".equals(span)) {
                                prod.setPublishing(aElement.getElementsByTag("a").text());
                            } else if ("ＩＳＢＮ:".equals(span)) {
                                prod.setIsbn(aElement.ownText());
                            } else if ("出版时间/Publication Date:".equals(span)) {
                                prod.setPublishDate(aElement.ownText());
                            } else if ("版　　次/Edition:".equals(span)) {
                                prod.setBanci(aElement.ownText());
                            } else if ("页　　数/Pages:".equals(span)) {
                                prod.setPageNum(aElement.ownText());
                            } else if ("装　　帧/Format:".equals(span)) {
                                prod.setPack(aElement.ownText());
                            } else if ("尺寸及重量/Dimensions & Weight:".equals(span)) {
                                String[] strs = aElement.ownText().split(";");
                                if(strs.length == 1) {
                                    prod.setSize(strs[0]);
                                } else if(strs.length == 2) {
                                    prod.setSize(strs[0]);
                                    prod.setWeight(strs[1]);
                                }
                            } else if ("纸　　张/Paper:".equals(span)) {
                                prod.setPaper(aElement.ownText());
                            } else if ("正文语种/Language:".equals(span)) {
                                prod.setLanguage(aElement.ownText());
                            }
                        }
                    } else { // 取summary
                        mElements = detailDoc.select("div.main div.right-extra > ul#summary");
                        summaryElements = mElements.first();
                        for (Element aElement : summaryElements.select("li")) {
                            span = aElement.getElementsByTag("span").text();
                            if ("作　　者：".equals(span)) {
                                Elements authorElements = aElement.getElementsByTag("a");
                                if (authorElements.size() > 0) {
                                    prod.setAuthor(authorElements.get(0).text());
                                }
                                if (authorElements.size() > 1) {
                                    prod.setTranslator(authorElements.get(1).text());
                                }
                                if (authorElements.size() > 2) {
                                    prod.setPainter(authorElements.get(2).text());
                                }
                            } else if ("出 版 社：".equals(span)) {
                                prod.setPublishing(aElement.getElementsByTag("a").text());
                            } else if ("ＩＳＢＮ：".equals(span)) {
                                prod.setIsbn(aElement.ownText());
                            } else if ("出版时间：".equals(span)) {
                                prod.setPublishDate(aElement.ownText());
                            } else if ("版　　次：".equals(span)) {
                                prod.setBanci(aElement.ownText());
                            } else if ("页　　数：".equals(span)) {
                                prod.setPageNum(aElement.ownText());
                            } else if ("装　　帧：".equals(span)) {
                                prod.setPack(aElement.ownText());
                            } else if ("开　　本：".equals(span)) {
                                prod.setKaiben(aElement.ownText());
                            } else if ("丛 书 名：".equals(span)) {
                                prod.setSeriesName(aElement.ownText());
                            }

                            else if (aElement.hasClass("extra")) {
                                for (Element e : aElement.select("div")) {
                                    String[] values = e.ownText().split("：");
                                    if(values.length == 2) {
                                        if ("纸　　张".equals(values[0].trim())) {
                                            prod.setPaper(values[1]);
                                        } else if ("正文语种".equals(values[0].trim())) {
                                            prod.setLanguage(values[1]);
                                        } else if ("印刷时间".equals(values[0].trim())) {
                                            prod.setPrintDate(values[1]);
                                        } else if ("印　　次".equals(values[0].trim())) {
                                            prod.setPrintNum(values[1]);
                                        } else if ("套装数量".equals(values[0].trim())) {
                                            prod.setSuitNum(values[1]);
                                        } else if ("读者对象".equals(values[0].trim())) {
                                            prod.setReaders(values[1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prod;
    }
}
