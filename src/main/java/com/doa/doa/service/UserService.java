package com.doa.doa.service;

import com.doa.doa.entity.User;

public interface UserService {
    void register(User user);
    boolean authenticate(String email, String password);
}
