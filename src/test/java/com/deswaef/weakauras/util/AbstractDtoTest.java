package com.deswaef.weakauras.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AbstractDtoTest {
    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public String readMarshalledFile(String s) throws IOException {
        URL url = this.getClass().getResource(s);
        File testProperties = new File(url.getFile());
        return readFile(URLDecoder.decode(testProperties.getPath()), Charset.defaultCharset());
    }

}
