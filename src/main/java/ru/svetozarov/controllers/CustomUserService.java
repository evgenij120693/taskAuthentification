package ru.svetozarov.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.svetozarov.common.exception.UserDAOException;
import ru.svetozarov.common.util.IHashPassword;
import ru.svetozarov.services.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgenij on 14.03.2017.
 */
public class CustomUserService implements UserDetailsService {
    private static Logger logger = Logger.getLogger(CustomUserService.class);
    private IUserService iUserService;

    private IHashPassword iHashPassword;

    @Autowired
    @Qualifier("userService")
    public void setiUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }
    @Autowired
    @Qualifier("hashPassword")
    public void setiHashPassword(IHashPassword iHashPassword) {
        this.iHashPassword = iHashPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            ru.svetozarov.models.pojo.User myUser = iUserService.getUserByLogin(username);
            List<GrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority(myUser.getRole()));
            UserDetails user = new User(
                    username, myUser.getPassword(), true,
                    true, true, true, list
            );
            return user;
        } catch (UserDAOException e) {
            logger.error(e);

        }
        throw new UsernameNotFoundException(username + " not found");
    }
}
