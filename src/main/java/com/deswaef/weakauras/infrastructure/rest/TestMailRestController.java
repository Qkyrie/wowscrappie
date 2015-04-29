package com.deswaef.weakauras.infrastructure.rest;

import com.deswaef.weakauras.infrastructure.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/admin/emails")
public class TestMailRestController {

    @Autowired
    private MailService mailService;

    @Value("${com.deswaef.scrappie.mailstore}")
    private String mailStore;

    @Value("${com.deswaef.scrappie.fullbaseurl}")
    private String fullBaseUrl;

    @RequestMapping(value = "/test")
    public HttpEntity<String> sendEmail() {
        try {
            mailService
                    .createMail()
                    .htmlBody(readFromEmails("testemail.html").replace("${com.deswaef.scrappie.fullbaseur}", fullBaseUrl))
                    .to("quintendeswaef@gmail.com")
                    .subject("WowScrappie - Activate your Registration")
                    .send();
            return new HttpEntity<>("email was sent");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new HttpEntity<>("Failure when sending: " + ex.getMessage());
        }
    }

    public static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }


    public String readFromEmails(String s) throws IOException {
        File htmlFile = new File(String.format("%s%s%s", mailStore, File.separator, s));
        return readFile(URLDecoder.decode(htmlFile.getPath()), Charset.defaultCharset());
    }

}
