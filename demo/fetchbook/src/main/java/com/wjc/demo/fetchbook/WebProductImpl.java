package com.wjc.demo.fetchbook;

import java.net.URL;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class WebProductImpl implements IWebProduct {
    private String name;
    private URL picture;
    private String hAbstract;
    private String content;
    private String authorIntro;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getPictureURL() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public String getHAbstract() {
        return hAbstract;
    }

    public void setHAbstract(String hAbstract) {
        this.hAbstract = hAbstract;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorIntro() {
        return authorIntro;
    }

    public void setAuthorIntro(String authorIntro) {
        this.authorIntro = authorIntro;
    }
}
