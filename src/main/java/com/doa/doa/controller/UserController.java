package com.doa.doa.controller;

import com.doa.doa.entity.User;
import com.doa.doa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public String register(User user) {
        userService.register(user);
        return "redirect:/member/login"; //회원가입 성공 후 로그인 페이지로 리다이렉트
    }

    @PostMapping("/login")
    public String Login(String email, String password, RedirectAttributes redirectAttributes){
        boolean loginSuccess = userService.authenticate(email,password);
        if(loginSuccess){
            return "redirect:/";  //로그인성공시 인덱스 페이지로 리다이렉트
        }else{
            // 로그인 실패 시, 로그인 페이지로 리다이렉트하고, 에러 메시지를 전달할 수 있습니다.
            redirectAttributes.addFlashAttribute("loginError", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/member/login"; //로그인 실패시 로그인 페이지로 리다이렉트
        }
    }

}