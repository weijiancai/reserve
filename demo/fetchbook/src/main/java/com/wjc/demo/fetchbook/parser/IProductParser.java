package com.wjc.demo.fetchbook.parser;

import com.wjc.demo.fetchbook.IWebProduct;

/**
 * 图书商品解析器
 *
 * @author weijiancai
 * @since 0.0.1
 */
public interface IProductParser {
    /**
     * 解析商品信息
     *
     * @return 返回商品信息
     */
    IWebProduct parse();
}
