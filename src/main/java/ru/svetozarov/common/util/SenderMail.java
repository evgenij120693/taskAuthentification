package ru.svetozarov.common.util;


import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Шмыга on 27.02.2017.
 */
@Service

public class SenderMail {
    private static Logger logger = Logger.getLogger(SenderMail.class);
    private  String username = "evgenij.svetozarov@yandex.ru";
    private   String password = "utugub39";

    /**
     * Отправка сообещния на email
     * @param email - адрес получателя
     * @param subject - тема письма
     * @param text - текст письма
     */
    public   void sendMail(String email, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.yandex.ru" );
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
    }
}
