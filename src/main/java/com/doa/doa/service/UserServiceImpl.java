package com.doa.doa.service;

import com.doa.doa.entity.User;
import com.doa.doa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public boolean authenticate(String email, String password) {
        //이메일로 사용자 찾기
        User user = userRepository.findByEmail(email);
        if(user !=null && user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
