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


//    private UserDetailsService userDetailsService;
//
//    private BCryptPasswordEncoder bcryptEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication)
//            throws AuthenticationException {
//        logger.info("authenticate started.");
//        String password = authentication.getCredentials().toString();
//        String userName = authentication.getName();
//        authentication.getPrincipal();
//        UserDetails userDetails = userDetailsService
//                .loadUserByUsername(userName);
//        User user =(User) userDetails ;//((UserDetailsImpl) userDetails).getUser();
//
//        if (user == null) {
//            logger.error("User not found. UserName=" + userName);
//            throw new BadCredentialsException(userName);
//        } else if (!user.isAccountNonLocked()) {
//            logger.error("Not activated.");
//            throw new UsernameNotFoundException(userName);
//        } else if (!user.isEnabled()) {
//            logger.error("Not enabled.");
//            throw new DisabledException(userName);
//        } else {
//            if (bcryptEncoder.matches(password, user.getPassword())) {
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                logger.info("authenticate finished.");
//                return token;
//            } else {
//                logger.error("Password does not match. UserName=" + userName);
//                throw new BadCredentialsException(userName);
//            }
//
//        }
//    }
//
//    @Override
//    public boolean supports(Class<? extends Object> authentication) {
//        return UsernamePasswordAuthenticationToken.class
//                .isAssignableFrom(authentication);
//    }
//
//    public void setUserService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    public void setBcryptEncoder(BCryptPasswordEncoder bcryptEncoder) {
//        this.bcryptEncoder = bcryptEncoder;
//    }
//
//    @Override
//    public String encodePassword(String rawPass, Object salt) {
//        return null;
//    }
//
//    @Override
//    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
//        return false;
//    }
}