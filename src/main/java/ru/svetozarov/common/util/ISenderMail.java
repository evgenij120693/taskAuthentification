package ru.svetozarov.common.util;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface ISenderMail {
    void sendMail(String email, String subject, String text);
}
