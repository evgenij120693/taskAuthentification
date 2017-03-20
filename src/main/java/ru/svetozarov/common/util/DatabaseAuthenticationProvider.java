package ru.svetozarov.common.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.svetozarov.common.exception.HashPasswordException;

// ... other imports


public final class DatabaseAuthenticationProvider implements
        PasswordEncoder {

    private static final Logger logger = Logger.getLogger(DatabaseAuthenticationProvider.class);

    private ru.svetozarov.common.util.IHashPassword IHashPassword;

    @Autowired
    @Qualifier("hashPassword")
    public void setHashPassword(IHashPassword IHashPassword) {
        this.IHashPassword = IHashPassword;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        String password = rawPassword.toString();
        try {
            password = IHashPassword.hashingPassword(password);
            return password;
        } catch (HashPasswordException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String password = rawPassword.toString();
        try {
            password = IHashPassword.hashingPassword(password);
            if (password.equals(encodedPassword)) {
                return true;
            } else {
                return false;
            }
        } catch (HashPasswordException e) {
            logger.error(e);
            return false;
        }
    }
}