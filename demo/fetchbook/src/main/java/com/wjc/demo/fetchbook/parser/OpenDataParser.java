package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;
import com.wjc.demo.fetchbook.SiteName;
import com.wjc.demo.fetchbook.WebProductImpl;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

/**
 * 亚马逊解析器
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class OpenDataParser implements IProductParser {
    public static final String SEARCH_URL = "http://www.openbookdata.com.cn/BookList/%s-0-0-0-1-1_0_0.html";
    public static final String LOGIN_URL = "http://www.openbookdata.com.cn/handlers/customer_foreHandler.ashx?type=3";
    public static final String CHECK_LOGIN_URL = "http://www.openbookdata.com.cn/handlers/common_foreHandler.ashx?type=2";
    private static final int TIME_OUT = 2 * 1000; // 2秒超时
    private static Map<String, String> cookiesMap = new HashMap<>();

    private static String isbn = "9787504480347";

    public OpenDataParser(String isbn) {
        this.isbn = isbn;
    }

    public static void main(String[] args) throws IOException {
        cookiesMap.put("ASP.NET_SessionId", "z2ovmoxq5d1exrk1qikaezzq");
        System.out.println("检查登陆");
        System.out.println("------------------------------------------------------");
        Document doc = Jsoup.connect(CHECK_LOGIN_URL).cookies(cookiesMap).get();
        System.out.println(doc.html());
        if (doc.html().contains("var isLogin=0;")) {
            System.out.println("登陆");
            System.out.println("------------------------------------------------------");
            login();
        }
        System.out.println("搜索图书");
        System.out.println("------------------------------------------------------");
        search();
    }

    private static void login() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("ln", "kukulang");
        params.put("pw", "87175185");
        params.put("ts", "" + new Date().getTime());
        Connection conn = Jsoup.connect(LOGIN_URL);
        Document doc = conn.data(params)
                .userAgent("Mozilla")
                .post();
        cookiesMap = conn.response().cookies();
        System.out.println(cookiesMap);
        System.out.println(doc.html());
    }

    private static void search() throws IOException {
        Connection conn = Jsoup.connect(String.format(SEARCH_URL, isbn));
        Document doc = conn.cookies(cookiesMap).get();
        System.out.println(doc.html());
    }

    @Override
    public IWebProduct parse() {
        WebProductImpl prod = new WebProductImpl();
        prod.setSourceSite(SiteName.AMAZON.name());
        List<URL> pictureUrlList = new ArrayList<URL>();
        try {
            // 登陆
            Map<String, String> params = new HashMap<>();
            params.put("ln", "kukulang");
            params.put("pw", "87175185");
            params.put("ts", "" + new Date().getTime());
            Connection conn;
            Document doc;
           /* conn = Jsoup.connect(LOGIN_URL);
            doc = conn
                    .data(params)
                    .userAgent("Mozilla")
                    .post();
            System.out.println(conn.response().cookies());
            System.out.println(doc.html());*/

            conn = Jsoup.connect(String.format(SEARCH_URL, isbn));
            System.out.println(conn.response().cookies());
            doc = conn
//                    .cookies(conn.response().cookies())
                    .cookie("ASP.NET_SessionId", "2gwukkbuqrt0wd5pqhayglhg")
                    .get();
            System.out.println(doc.html());
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("d:/opendata.html"), "UTF-8"));
            pw.write(doc.html());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prod;
    }
}
