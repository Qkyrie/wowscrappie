package com.deswaef.wowscrappie.importingservice.wowhead.client.dto;

import com.deswaef.wowscrappie.importingservice.infrastructure.exception.HeurekaException;
import com.deswaef.wowscrappie.importingservice.wowhead.client.mapper.WowheadItemMapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Optional;

@XmlRootElement(name = "wowhead")
public class WowheadItemDto {

    @XmlElement(name = "item")
    private WowheadInnerItem item;

    public WowheadInnerItem getItem() {
        return item;
    }

    public static Optional<WowheadItemDto> fromXml(String input) {
        if (input != null && !input.isEmpty()) {
            try {
                return Optional.ofNullable(WowheadItemMapper.convert(input, WowheadItemDto.class));
            } catch (Exception e) {
                throw new HeurekaException("unable to convert " + input, e);
            }
        } else {
            throw new HeurekaException("input from wowhead was empty");
        }
    }
}
