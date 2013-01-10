package com.wjc.demo.fetchbook;

import com.wjc.demo.fetchbook.parser.AmazonParser;
import com.wjc.demo.fetchbook.parser.DangDangParser;
import com.wjc.demo.fetchbook.parser.DouBanParser;
import com.wjc.demo.fetchbook.parser.JingDongParser;

/**
 * 网上电子书商产品信息工场类
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class WebProductFactory {
    /**
     * 获取京东上的图书信息，如果有多条，取第一条
     *
     * @param isbn ISBN
     * @return 返回图书信息，如果没有找到返回null
     */
    public static IWebProduct getJingDongProduct(String isbn) {
        return new JingDongParser(isbn).parse();
    }

    /**
     * 获取当当上的图书信息，如果有多条，取第一条
     *
     * @param isbn ISBN
     * @return 返回图书信息，如果没有找到返回null
     */
    public static IWebProduct getDangDangProduct(String isbn) {
        return new DangDangParser(isbn).parse();
    }

    /**
     * 获取豆瓣上的图书信息，如果有多条，取第一条
     *
     * @param isbn ISBN
     * @return 返回图书信息，如果没有找到返回null
     */
    public static IWebProduct getDouBanProduct(String isbn) {
        return new DouBanParser(isbn).parse();
    }

    /**
     * 获取亚马逊上的图书信息，如果有多条，取第一条
     *
     * @param isbn ISBN
     * @return 返回图书信息，如果没有找到返回null
     */
    public static IWebProduct getAmazonProduct(String isbn) {
        return new AmazonParser(isbn).parse();
    }

    /**
     * 获取网站上的图书信息，如果有多条，取第一条
     *
     * @param isbn ISBN
     * @return 返回图书信息，如果没有找到返回null
     */
    public static IWebProduct getProduct(SiteName siteName, String isbn) {
        switch (siteName) {
            case JING_DONG:
                return getJingDongProduct(isbn);
            case AMAZON:
                return getAmazonProduct(isbn);
            case DANG_DANG:
                return getDangDangProduct(isbn);
            case DOU_BAN:
                return getDouBanProduct(isbn);
        }

        return null;
    }
}
