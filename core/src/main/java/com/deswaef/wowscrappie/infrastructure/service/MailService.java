package com.deswaef.wowscrappie.infrastructure.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MailService {

    private Log logger = LogFactory.getLog(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.default.footer}")
    private String footer;

    @Value("${mail.default.address.from}")
    private String defaultFromEmailAddress;

    @Value("${mail.default.address.to}")
    private String defaultToEmailAddress;

    public Mail createMail() {
        return new Mail();
    }

    private void sendMail(Mail mail) {
        MimeMessage message = createMessage(mail);
        mailSender.send(message);
    }

    private MimeMessage createMessage(Mail mail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mail.from.orElse(defaultFromEmailAddress));
            helper.setTo(mail.to.orElse(defaultToEmailAddress));
            helper.setSubject(mail.subject.orElse(""));
            helper.setText(addFooter(mail.body.orElse(""), mail.html), mail.html);
            for (Attachment attachment : mail.attachments) {
                helper.addAttachment(attachment.name, attachment.inputStreamSource);
            }
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send e-mail", e);
        }
        return message;
    }

    private String addFooter(String body, Boolean html) {
        if (footer != null && footer.length() > 0) {
            String lineSeparator = html ? "<br />" : System.lineSeparator();
            return body + lineSeparator + lineSeparator + footer;
        }
        return body;
    }

    public class Mail {
        private Optional<String> from = Optional.empty();
        private Optional<String> to = Optional.empty();
        private Optional<String> subject = Optional.empty();
        private Optional<String> body = Optional.empty();
        private Boolean html = false;
        private List<Attachment> attachments = new ArrayList<>();

        public Mail from(String from) {
            this.from = Optional.ofNullable(from);
            return this;
        }

        public Mail to(String to) {
            this.to = Optional.ofNullable(to);
            return this;
        }

        public Mail subject(String subject) {
            this.subject = Optional.ofNullable(subject);
            return this;
        }

        public Mail body(String body) {
            this.body = Optional.ofNullable(body);
            return this;
        }

        public Mail attachment(String name, String contents) {
            attachments.add(new Attachment(name, new ByteArrayResource(contents.getBytes())));
            return this;
        }

        public Mail htmlBody(String body) {
            this.body = Optional.ofNullable(body);
            this.html = true;
            return this;
        }

        public void send() {
            sendMail(this);
            logger.info("mail was sent to " + this.to.orElse("unknown"));
        }
    }

    private class Attachment {
        private String name;
        private InputStreamSource inputStreamSource;

        public Attachment(String name, InputStreamSource inputStreamSource) {
            this.name = name;
            this.inputStreamSource = inputStreamSource;
        }
    }


}
