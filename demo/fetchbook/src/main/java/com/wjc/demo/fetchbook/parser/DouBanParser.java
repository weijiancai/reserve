package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import com.wjc.demo.fetchbook.SiteName;
import com.wjc.demo.fetchbook.WebProductImpl;
import com.wjc.demo.fetchbook.doanload.DownloadImage;
import com.wjc.demo.fetchbook.util.UtilString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

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
            String searchResult = doc.select("div#content div.article > div.trr").text();
            try {
                int result = Integer.parseInt(UtilString.trim(searchResult.substring(searchResult.indexOf("共") + 1)));
                if (result == 0) {
                    return prod;
                }
            } catch (NumberFormatException e) {
                return prod;
            }

//            Elements elements = doc.select("div#content div.article > table tr.item");
            Elements elements = doc.select("div#content div.article ul.subject-list li.subject-item");
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
                    prod.setName(UtilString.trim(detailDoc.select("div#wrapper > h1 > span").first().ownText()));
                    // 取图片
//                    prod.setPicture(new URL(detailDoc.select("div#content div.article div#mainpic > a").first().attr("href")));
                    prod.setPictureURLs(new URL[]{new URL(detailDoc.select("div#content div.article div#mainpic > a").first().attr("href"))});
                    // 取基本信息
                    String infoHtml = detailDoc.select("div#content div.article div#info").first().html();
                    String[] infoStrs = infoHtml.split("<br />");
                    for (String infoStr : infoStrs) {
                        if (infoStr.contains("作者")) {
                            prod.setAuthor(Jsoup.parse(infoStr).select("a").text());
                        } else if (infoStr.contains("出版社")) {
                            prod.setPublishing(Jsoup.parse(infoStr).select("body").first().ownText());
                        } else if (infoStr.contains("出版年")) {
                            prod.setPublishDate(Jsoup.parse(infoStr).select("body").first().ownText());
                        } else if (infoStr.contains("页数")) {
                            prod.setPageNum(UtilString.trim(Jsoup.parse(infoStr).select("body").first().ownText().replace("页", "")));
                        } else if (infoStr.contains("定价")) {
                            prod.setPrice(Jsoup.parse(infoStr).select("body").first().ownText().replace("元", ""));
                        } else if (infoStr.contains("ISBN")) {
                            prod.setIsbn(isbn);
                        } else if (infoStr.contains("装帧")) {
                            prod.setPack(Jsoup.parse(infoStr).select("body").first().ownText());
                        } else if (infoStr.contains("译者")) {
                            prod.setTranslator(Jsoup.parse(infoStr).select("a").text());
                        }
                    }

                    mElements = detailDoc.select("div#content div.article > div.related_info > h2");
                    for (Element aElement : mElements) {
                        String text = aElement.text();
                        // 去掉<script>
                        aElement.nextElementSibling().select("script").remove();
                        String siblingText = aElement.nextElementSibling().html();
                        if (text.contains("内容简介")) {
                            prod.setContent(siblingText);
                        } else if (text.contains("作者简介")) {
                            prod.setAuthorIntro(siblingText);
                        } else if (text.contains("目录")) {
                            prod.setCatalog(siblingText);
                        }
                    }

                    // 下载图片
                    new DownloadImage(prod.getPictureURLs());
                    for (URL url : prod.getPictureURLs()) {

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prod;
    }
}
