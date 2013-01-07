package com.wjc.demo.fetchbook;

import java.net.URL;

/**
 * 网上电子书商产品信息
 *
 * @author weijiancai
 * @since 0.0.1
 */
public interface IWebProduct {
    /**
     * 获取商品的图片信息
     *
     * @return 返回商品的图片输入流
     */
    URL getPictureURL();

    /**
     * 获取商品名称
     *
     * @return 返回商品名称
     */
    String getName();

    /**
     * 获取商品概述
     *
     * @return 返回商品概述信息
     */
    String getHAbstract();

    /**
     * 获取商品内容信息
     *
     * @return 返回商品内容信息
     */
    String getContent();

    /**
     * 获取作者简介
     *
     * @return 返回作者简介信息
     */
    String getAuthorIntro();
}
