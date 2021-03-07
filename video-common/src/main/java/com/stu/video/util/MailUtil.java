package com.stu.video.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @Author: kimijiaqili
 * @CreateDate: 2020/12/25 13:58
 * @Version: 1.0
 * @Description:
 */
@Component
public class MailUtil {
    public static final String mailSubject = "验证码";
    public static final String mailContent = "您的验证码是：%s";

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendVerificationMail(String toEmail, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(mailSubject);
        String sendContent = String.format(mailContent, verificationCode);
        message.setText(sendContent);
        mailSender.send(message);
    }
}
