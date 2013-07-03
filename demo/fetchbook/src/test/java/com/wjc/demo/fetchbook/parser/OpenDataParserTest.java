package com.wjc.demo.fetchbook.parser;

import org.junit.Test;

/**
 * @author weijiancai
 * @version 0.0.1
 */
public class OpenDataParserTest {
    @Test
    public void testParse() throws Exception {
        OpenDataParser parser = new OpenDataParser("9787504479037");
        parser.parse();
    }
}
