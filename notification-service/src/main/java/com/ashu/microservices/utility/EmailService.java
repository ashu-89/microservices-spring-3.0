package com.ashu.microservices.utility;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j

@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {

        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {

            MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage);
            msgHelper.setFrom("noreply@ashuzshop.com");
            msgHelper.setTo(to);
            msgHelper.setSubject(subject);
            msgHelper.setText(body);
        };

        try{
            javaMailSender.send(mimeMessagePreparator);
            log.info("Email sent successfully to {}", to);
        } catch (MailException e) {
            log.error("Failed to send email to {}", to);
            throw new RuntimeException(e);
        }

    }
}
