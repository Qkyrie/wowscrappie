package com.deswaef.wowscrappie.importingservice.wowhead.client.mapper;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class WowheadItemMapper {
    private WowheadItemMapper(){}
    public static <T> T  convert(String input, Class<T> convertInto) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(convertInto);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StringReader reader = new StringReader(input);
        return (T) unmarshaller.unmarshal(reader);
    }
}
