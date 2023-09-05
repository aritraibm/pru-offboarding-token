package com.pru.token.app.user.service;

import com.pru.token.app.user.User;
import com.pru.token.app.user.api.RequestUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User createUser(RequestUser user);
}
