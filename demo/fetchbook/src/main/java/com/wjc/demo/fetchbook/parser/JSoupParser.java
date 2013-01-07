package com.wjc.demo.fetchbook.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSoup解析器
 *
 * @author weijiancai
 * @since 0.0.1
 */
public class JSoupParser implements IParser {
    private Map<String, Document> docMap = new HashMap<String, Document>();


    private Document getDocument(String url) {
        Document doc = docMap.get(url);
        try {
            if (doc == null) {
                docMap.put(url, Jsoup.connect(url).get());
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("连接url【%s】失败！！！", url), e);
        }

        return doc;
    }

    @Override
    public String selectValue(String url, String cssQuery) {
        Document doc = getDocument(url);
        if (doc != null) {
            Elements elements = doc.select(cssQuery);
            if (elements != null && elements.size() > 0) {
                return elements.first().ownText();
            }
        }
        return null;
    }

    @Override
    public List<SearchResult> search(String url) {
        Document doc = getDocument(url);
        return null;
    }
}
