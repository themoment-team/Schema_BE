package com.backend.schemabackend.controller;


import com.backend.schemabackend.auth.MyMemberDetail;
import com.backend.schemabackend.entity.Member;
import com.backend.schemabackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String SignUpage(){
        return "pages/SignUp";
    }

    @PostMapping("/signUp")
    public String signUp(Member member) {
        member.setRole("USER");
        memberService.joinMember(member);
        log.info(member.getUserid());
        return "redirect:/pages/SignIn";
    }

    @GetMapping("/login")
    public String loginpage(){
        return "pages/SignIn";
    }

    @GetMapping("/index")
    public String indexpage(){
        return "index";
    }

    @GetMapping("/")
    public String userAccess(Model model, Authentication authentication) {
        MyMemberDetail userDetail = (MyMemberDetail)authentication.getPrincipal();
        log.info(userDetail.getUsername());
        model.addAttribute("info", userDetail.getUsername());
        return "pages/main";
    }
}
