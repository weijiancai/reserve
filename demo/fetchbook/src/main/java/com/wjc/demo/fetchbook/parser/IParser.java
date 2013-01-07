package com.wjc.demo.fetchbook.parser;

import java.util.List;

/**
 * 解析器接口
 *
 * @author weijiancai
 * @since 0.0.1
 */
public interface IParser {
    /**
     * 使用类似于CSS或jQuery的语法来查找元素的值，如果找到，返回第一个元素的text值。
     *
     * @param url url地址
     * @param cssQuery 类似于CSS或jQuery的语法
     * @return 如果找到，返回第一个元素的text值。否则返回null
     */
    String selectValue(String url, String cssQuery);

    /**
     * 搜索图书
     *
     * @param url url地址
     * @return 返回搜索结果列表
     */
    List<SearchResult> search(String url);
}
