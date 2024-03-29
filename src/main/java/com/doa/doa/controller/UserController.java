package com.doa.doa.controller;

import com.doa.doa.entity.User;
import com.doa.doa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public String register(User user) {
        System.out.println("회원가입오류확인");
        //회원 가입 로직
        userService.register(user);
        return "redirect:login"; //회원가입 성공 후 로그인 페이지로 리다이렉트
    }

}