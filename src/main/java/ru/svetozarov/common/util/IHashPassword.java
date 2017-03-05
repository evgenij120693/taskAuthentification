package ru.svetozarov.common.util;

import ru.svetozarov.common.exception.HashPasswordException;

/**
 * Created by Evgenij on 05.03.2017.
 */
public interface IHashPassword {
    String hashingPassword(String password) throws HashPasswordException;
}
