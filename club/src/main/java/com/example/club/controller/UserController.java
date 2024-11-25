package com.example.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.club.dto.ClubMemberDto;
import com.example.club.service.ClubUserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/users")
@Log4j2
@RequiredArgsConstructor
@Controller
public class UserController {

    private final ClubUserService service;

    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public void getLogin() {
        log.info("로그인 폼 요청");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void getMember() {
        log.info("member 페이지 요청");
    }

    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @GetMapping("/admin")
    public void getAdmin() {
        log.info("admin 페이지 요청");
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/register")
    public void getRegister(ClubMemberDto dto) {
        log.info("회원 가입 폼 요청");
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public String postRegister(@Valid ClubMemberDto dto, BindingResult result, RedirectAttributes rttr) {
        log.info("회원 가입 요청 {}", dto);

        if (result.hasErrors()) {
            return "/users/register";
        }

        // 서비스호출
        String email = service.register(dto);

        rttr.addFlashAttribute("email", email);
        return "redirect:/users/login";
    }

    @PreAuthorize("permitAll()")
    @ResponseBody
    @GetMapping("/auth")
    public Authentication getAuthentication() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication;
    }

}