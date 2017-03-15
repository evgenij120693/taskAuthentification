package ru.svetozarov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ru.svetozarov.common.util.IHashPassword;
import ru.svetozarov.services.IUserService;

/**
 * Created by Evgenij on 14.03.2017.
 */
@Controller
public class LoginController {
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


}
