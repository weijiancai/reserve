package com.wjc.jdom;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class TestParseCDATA {
    @Test
    public void testParseCDATA() throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
//        Document doc = builder.build(new StringReader(getXml()));
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\workspace\\reserve\\demo\\fetchbook\\src\\test\\resources\\product00000032.xml"), "gbk");
        Document doc = builder.build(isr);
//        String cdata = doc.getRootElement().getChild("text").getText();
//        System.out.println(cdata);
        System.out.println(doc);
    }

    private String getXml() {
        return "<root><text><![CDATA[\"<!--<a></a>abc\"]]></text></root>";
    }
}
