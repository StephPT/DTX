package com.steph.dtx.web.service;

import com.steph.dtx.database.dao.UserRepository;
import com.steph.dtx.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser() {
        return userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
