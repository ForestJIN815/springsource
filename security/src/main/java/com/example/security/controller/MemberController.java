package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/member")
@Log4j2
@Controller
public class MemberController {

    @GetMapping("/login")
    public void getLogin() {
        log.info("로그인 폼 요청");
    }

}
