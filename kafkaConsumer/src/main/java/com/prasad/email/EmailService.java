package com.prasad.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

   

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSenderImpl mailSender;

    @Override
    @Async
    public void send(String toEmail, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body, true);
            helper.setSubject("Confirm email");
            helper.setFrom("prasadpadala2005@gmail.com");
            helper.setTo(toEmail);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            LOGGER.error("falied to send email", e);
            throw new IllegalStateException("faliled to send email");
        }
    }


    

}
