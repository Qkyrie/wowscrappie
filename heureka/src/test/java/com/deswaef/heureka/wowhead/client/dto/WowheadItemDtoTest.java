package com.deswaef.heureka.wowhead.client.dto;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WowheadItemDtoTest {

    @Test
    public void testFromMarshal() throws IOException {
        String s = readMarshalledFile("/wowhead/wowheaditem.xml");
        Optional<WowheadItemDto> wowheadItemDto = WowheadItemDto.fromXml(s);
        assertThat(wowheadItemDto).isNotNull();
        assertThat(wowheadItemDto.get().getItem()).isNotNull();
        assertThat(wowheadItemDto.get().getItem().getLevel()).isEqualTo(232);
        assertThat(wowheadItemDto.get().getItem().getName().trim()).isEqualToIgnoringCase("Seethe");
        assertThat(wowheadItemDto.get().getItem().getQuality().trim()).isEqualTo("Epic");

    }

    public static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public String readMarshalledFile(String s) throws IOException {
        URL url = this.getClass().getResource(s);
        File testProperties = new File(url.getFile());
        return readFile(URLDecoder.decode(testProperties.getPath()), Charset.defaultCharset());
    }

}