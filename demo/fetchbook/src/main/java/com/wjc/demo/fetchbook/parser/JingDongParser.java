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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class JingDongParser implements IProductParser {
    public static final String SEARCH_URL = "http://search.jd.com/Search?enc=utf-8&book=y&keyword=";
    public static final String JOURNAL_SEARCH_URL = "http://search.jd.com/bookadvsearch?keyword=%s&isbn=%s";

    private String isbn;

    public JingDongParser(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public IWebProduct parse() {
        WebProductImpl prod = new WebProductImpl();
        prod.setSourceSite(SiteName.JING_DONG.name());
        List<URL> pictureUrlList = new ArrayList<URL>();
        try {
            // 打开搜索结果页面
            Document doc;
            if (isbn.startsWith("977")) {
                String year = Calendar.getInstance().get(Calendar.YEAR) + "";
                doc = Jsoup.connect(String.format(JOURNAL_SEARCH_URL, year, isbn)).get();
            } else {
                doc = Jsoup.connect(SEARCH_URL + isbn).get();
            }
            Elements elements = doc.select("div.main div.right-extra div#plist div.item");
            if (elements != null) {
                /*for (Element element : elements) {

                }*/
                // 取第一个
                if (elements.size() > 0) {
                    Element element = elements.first();
                    Elements mElements;
                    // 取搜索结果图片 160 * 160
                    pictureUrlList.add(new URL(element.select("div.p-img a img").first().attr("data-lazyload")));
                    // 打开详细页面
                    String detailUrl = element.select("div.p-img a").first().attr("href");

                    Document detailDoc = Jsoup.connect(detailUrl).get();
                    // 取书名
                    prod.setName(detailDoc.select("div.w div.right div#name h1").first().ownText());
                    // 取详细页面图片 280 * 280
                    URL bigPicUrl = getBigPic(detailDoc);
                    if (bigPicUrl == null) {
                        pictureUrlList.add(new URL(detailDoc.select("div.w div.right div#preview div#spec-n1 img").first().attr("src")));
                    } else {
                        pictureUrlList.add(bigPicUrl);
                    }

                    // 取定价
                    prod.setPrice(detailDoc.select("div.w div.right ul#summary li#summary-market div.dd").first().text().replace("￥", "").replace(" ", "").trim());
                    // 取作者
                    String authorStr = detailDoc.select("div.w div.right ul#summary li#summary-author div.dd").first().text();
                    StringBuilder sb = new StringBuilder();
                    for(char c : authorStr.toCharArray()) {
                        if(c == '著') {
                            prod.setAuthor(UtilString.trim(sb.toString()));
                            sb = new StringBuilder();
                        } else if(c == '译') {
                            prod.setTranslator(UtilString.trim(sb.toString()));
                            sb = new StringBuilder();
                        } else if(c == '绘') {
                            prod.setPainter(UtilString.trim(sb.toString()));
                            sb = new StringBuilder();
                        } else {
                            sb.append(c);
                        }
                    }
                    // 取出版社
                    prod.setPublishing(detailDoc.select("div.w div.right ul#summary li#summary-ph div.dd").first().text());
                    // 取出版时间
                    prod.setPublishDate(detailDoc.select("div.w div.right ul#summary li#summary-published div.dd").first().text());
                    // 取ISBN
                    prod.setIsbn(detailDoc.select("div.w div.right ul#summary li#summary-isbn div.dd").first().text());
                    // 取商品介绍
                    mElements = detailDoc.select("div.w div.right div#product-detail div#product-detail-1 > ul li");
                    for (Element aElement : mElements) {
                        String text = aElement.ownText();
                        if (text.startsWith("版次：")) { // 取版次
                            prod.setBanci(aElement.ownText().substring(3).trim());
                        } else if (text.startsWith("装帧：")) { // 取装帧
                            prod.setPack(aElement.ownText().substring(3).trim());
                        } else if (text.startsWith("纸张：")) { // 取纸张
                            prod.setPaper(aElement.ownText().substring(3).trim());
                        } else if (text.startsWith("印刷时间：")) { // 取印刷时间
                            prod.setPrintDate(aElement.ownText().substring(5).trim());
                        } else if (text.startsWith("印次：")) { // 取印次
                            prod.setPrintNum(aElement.ownText().substring(3).trim());
                        } else if (text.startsWith("正文语种：")) { // 取正文语种
                            prod.setLanguage(aElement.ownText().substring(5).trim());
                        } else if (text.startsWith("开本：")) { // 取开本
                            prod.setKaiben(aElement.ownText().substring(3).trim());
                        } else if (text.startsWith("页数：")) { // 取页数
                            prod.setPageNum(aElement.ownText().substring(3).trim());
                        } else if (text.startsWith("作者：")) { // 作者
                            prod.setAuthor(aElement.ownText().substring(3).trim());
                        } else if (text.startsWith("尺寸：")) { // 尺寸
                            String[] strs = aElement.ownText().substring(3).trim().split(";");
                            if(strs.length == 1) {
                                prod.setSize(strs[0]);
                            } else if(strs.length == 2) {
                                prod.setSize(strs[0]);
                                prod.setWeight(strs[1].replace("kg", ""));
                            }
                            if(prod.getSize() != null) {
                                strs = prod.getSize().replace("cm", "").split("x");
                                if(strs.length == 3) {
                                    prod.setLength(strs[0]);
                                    prod.setWidth(strs[1]);
                                    prod.setDeep(strs[2]);
                                }
                            }
                        }
                    }

                    // 取其他信息
                    mElements = detailDoc.select("div.w div.right div#product-detail div#product-detail-1 > div.sub-m");
                    for (Element aElement : mElements) {
                        String title = aElement.select("div.sub-mt h3").text();
                        String value = aElement.select("div.sub-mc").html();
                        if("内容简介".equals(title)) { // 内容简介
                            prod.setContent(value);
                        } else if("作者简介".equals(title)) { // 作者简介
                            prod.setAuthorIntro(value);
                        } else if("精彩书摘".equals(title)) { // 取精彩书摘
                            prod.setExtract(value);
                        } else if("编辑推荐".equals(title)) { // 编辑推荐
                            prod.setHAbstract(value);
                        } else if("媒体评论".equals(title)) { // 媒体评论
                            prod.setMediaFeedback(value);
                        } else if("目录".equals(title)) { // 目录
                            prod.setCatalog(value);
                        } else if("前言".equals(title)) { // 前言
                            prod.setPrologue(value);
                        }
                    }

                    // 设置图片
                    prod.setPictureURLs(pictureUrlList.toArray(new URL[pictureUrlList.size()]));
                } else {
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prod;
    }

    private URL getBigPic(Document detailDoc) {
        Elements elements = detailDoc.getElementsByTag("script");

        try {
            for (Element element : elements) {
                String text = element.html();
                if(text != null) {
                    text = text.trim();
                    if (text.startsWith("window.pageConfig=") && text.contains("jqimg")) {
                        int idx = text.indexOf("\"jqimg\":\"");
                        if (idx != -1) {
                            int endIdx = text.indexOf("\"", idx + 9);
                            String url = text.substring(idx + 9, endIdx).replace("\\", "");
                            return new URL(url);
                        }
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
