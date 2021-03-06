package ru.svetozarov.common.util;

import org.springframework.stereotype.Service;
import ru.svetozarov.common.exception.HashPasswordException;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Evgenij on 04.03.2017.
 */
@Service(value = "hashPassword")
public class HashPassword implements IHashPassword {
    private static Logger logger = Logger.getLogger(HashPassword.class);
    private  final String salt = "lrufbaeqw,vxurdahj";

    @Override
    public  String hashingPassword(String password) throws HashPasswordException {
        MessageDigest digest = null;
        String sha256hex;
        byte[] hash;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(password.getBytes());
            password = new String(Hex.encode(hash));
            password +=salt;
            hash = digest.digest(password.getBytes());
            password = new String(Hex.encode(hash));
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            throw new HashPasswordException();
        }
        return password;
    }

}
