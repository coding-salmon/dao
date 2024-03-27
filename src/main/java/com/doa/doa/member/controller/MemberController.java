package com.doa.doa.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    //회원가입 페이지 출력 요청

    @GetMapping("/member/join")
    public String joinForm(){

        return "join";
    }

    @PostMapping("/member/save")
    public String sa(){
        System.out.println("MemberController.save");
        return null;
}
}