package com.wjc.demo.fetchbook;

import com.wjc.demo.fetchbook.parser.JingDongParser;

/**
 * 网上电子书商产品信息工场
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class WebProductFactory {
    public static IWebProduct getJingDongProduct(String isbn) {
//        return new WebProduct360Buy(new JSoupParser(), isbn);
        return new JingDongParser(isbn).parse();
    }

    public static IWebProduct getProduct(SiteName siteName, String isbn) {
        switch (siteName) {
            case JING_DONG:
                return getJingDongProduct(isbn);
        }

        return null;
    }
}
