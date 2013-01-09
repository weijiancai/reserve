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

/**
 * 豆瓣解析器
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class DouBanParser implements IProductParser {
    public static final String SEARCH_URL = "http://book.douban.com/subject_search?cat=1001&search_text=";
    private static final int TIME_OUT = 2 * 1000; // 2秒超时

    private String isbn;

    public DouBanParser(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public IWebProduct parse() {
        WebProductImpl prod = new WebProductImpl();
        prod.setSourceSite(SiteName.DOU_BAN.name());
        try {
            // 打开搜索结果页面
            Document doc = Jsoup.connect(SEARCH_URL + isbn).timeout(TIME_OUT).get();
            Elements elements = doc.select("div#content > div#searchTemplate > div#rightContainerATF > div#rightResultsATF > div#center > div#atfResults > div");
            if (elements != null) {
                /*for (Element element : elements) {

                }*/
                // 取第一个
                if (elements.size() > 0) {
                    Element element = elements.first();
                    Elements mElements;
                    // 打开详细页面
                    String detailUrl = element.select("div.productImage a").first().attr("href");

                    Document detailDoc = Jsoup.connect(detailUrl)
                            .timeout(TIME_OUT)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .get();
                    // 取书名
                    prod.setName(UtilString.trim(detailDoc.select("form#handleBuy > div.buying > h1 > span").first().ownText()));
                   /* // 取图片
                    prod.setPicture(new URL(detailDoc.select("form#handleBuy > table.productImageGrid img#original-main-image").first().attr("src")));
                    // 取定价
                    prod.setPrice(detailDoc.select("form#handleBuy div#priceBlock span#listPriceValue").first().text().replace("￥", "").trim());
                    // 取作者
                    prod.setAuthor(detailDoc.select("form#handleBuy > div.buying > a").first().text());
                    // 取内容简介
                    prod.setContent(detailDoc.select("div#divsinglecolumnminwidth > div#ps-content > div.content").text());

                    // 取基本信息
                    mElements = detailDoc.select("div#divsinglecolumnminwidth > table td.bucket > div.content > ul > li");
                    for (Element aElement : mElements) {
                        String bTagText = aElement.select("b").text();
                        String value = UtilString.trim(aElement.ownText());
                        if (bTagText.contains("出版社")) {
                            String[] strs = value.split(";");
                            if (strs.length > 0) {
                                if (strs[0].contains("(")) {
                                    // 取出版社
                                    prod.setPublishing(UtilString.trim(strs[0].substring(0, strs[0].indexOf("("))));
                                    // 取出版时间
                                    prod.setPublishDate(strs[0].substring(strs[0].indexOf("(") + 1, strs[0].length() - 1));
                                } else {
                                    // 取出版社
                                    prod.setPublishing(strs[0]);
                                }
                            }
                            if (strs.length > 1) {
                                String ts = strs[1].replace("第", "").replace("版", "").replace(")", "");
                                String[] tss = ts.split("\\(");
                                if (tss.length == 2) {
                                    // 取版次
                                    prod.setBanci(UtilString.trim(tss[0]));
                                    // 取出版时间
                                    prod.setPublishDate(UtilString.trim(tss[1]));
                                }
                            }
                        } else if (bTagText.contains("平装")) {
                            prod.setPack("平装");
                            prod.setPageNum(value.replace("页", ""));
                        } else if (bTagText.contains("精装")) {
                            prod.setPack("精装");
                            prod.setPageNum(value.replace("页", ""));
                        } else if (bTagText.contains("简装")) {
                            prod.setPack("简装");
                            prod.setPageNum(value.replace("页", ""));
                        } else if (bTagText.contains("语种")) {
                            prod.setLanguage(value);
                        } else if (bTagText.contains("开本")) {
                            prod.setKaiben(value);
                        } else if (bTagText.contains("ISBN")) {
                            prod.setIsbn(isbn);
                        } else if (bTagText.contains("商品尺寸")) {
                            prod.setSize(value);
                        } else if (bTagText.contains("商品重量")) {
                            prod.setWeight(value);
                        }
                    }

                    // 取商品描述
                    mElements = detailDoc.select("div#divsinglecolumnminwidth > div#productDescription > div.content > div.seeAll > a");
                    if (mElements.size() > 0) {
                        String subDetailUrl = mElements.first().attr("href");
                        Document subDetailDoc = Jsoup.connect(subDetailUrl).timeout(TIME_OUT).get();
                        mElements = subDetailDoc.select("div#divsinglecolumnminwidth > div#productDescription > div.content > h3");
                    } else {
                        mElements = detailDoc.select("div#divsinglecolumnminwidth > div#productDescription > div.content > h3");
                    }
                    for (Element aElement : mElements) {
                        String text = aElement.text();
                        String siblingText = aElement.nextElementSibling().text();
                        if ("编辑推荐".equals(text)) {
                            prod.setHAbstract(siblingText);
                        } else if ("作者简介".equals(text)) {
                            prod.setAuthorIntro(siblingText);
                        } else if ("目录".equals(text)) {
                            prod.setCatalog(siblingText);
                        } else if ("序言".equals(text)) {
                            prod.setPrologue(siblingText);
                        } else if ("文摘".equals(text)) {
                            prod.setExtract(siblingText);
                        } else if ("媒体推荐".equals(text)) {
                            prod.setMediaFeedback(siblingText);
                        }
                    }*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prod;
    }
}
