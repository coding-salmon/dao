package com.doa.doa.member.controller;

import com.doa.doa.entity.User;
import com.doa.doa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final UserService userService;
    //회원가입 페이지 출력 요청

    @Autowired
    public MemberController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signup(User user){
        userService.register(user);
        return "redirect:/login"; //회원가입 성공 후 로그인 페이지로 리다이렉트
}
}