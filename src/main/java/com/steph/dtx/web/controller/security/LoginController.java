package com.steph.dtx.web.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String MESSAGE = "The Username or Password entered is incorrect.";

    private boolean errored = false;

    public static String getMESSAGE() {
        return MESSAGE;
    }

    public boolean isErrored() {
        return errored;
    }

    public void setErrored(boolean errored) {
        passwordEncoder.encode("Snowman25451855!");
        this.errored = errored;
    }

}
