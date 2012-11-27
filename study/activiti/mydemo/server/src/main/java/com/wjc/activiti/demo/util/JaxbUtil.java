package com.wjc.activiti.demo.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

/**
 * @author weijiancai
 * @since 0.0.1
 */
public class JaxbUtil {
    public static <T> String marshalList(List<T> list, Class<?> clazz) throws JAXBException {
        ListBean<T> listBean = new ListBean<>(list);
        JAXBContext context = JAXBContext.newInstance(ListBean.class, clazz);
        Marshaller marshaller = context.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.marshal(listBean, sw);

        return sw.toString();
    }
}
