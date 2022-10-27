package com.backend.schemabackend.controller;



import com.backend.schemabackend.auth.MyMemberDetail;
import com.backend.schemabackend.dto.ResponseDto;
import com.backend.schemabackend.entity.Member;
import com.backend.schemabackend.repository.BoardRepository;
import com.backend.schemabackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardRepository boardRep;

    @Autowired
    public MemberController(BoardRepository boardRep) {
        this.boardRep = boardRep;
    }

    @PostMapping("/signupInfo")
    public ResponseDto<Integer> save(@RequestBody Member member){
        System.out.println("MemberController : save 함수 호출됨");
        memberService.SignUp(member);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @PostMapping("/loginInfo")
    public ResponseDto<Integer> login(@RequestBody Member member, HttpSession session){
        System.out.println("MemberController : login 함수 호출됨");
        Member principal = memberService.SignIn(member);
        if(principal!=null){
            session.setAttribute("principal",principal);
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        else
            return new ResponseDto<Integer>(HttpStatus.NOT_FOUND.value(),1);
    }

    @GetMapping("/")
    public String main(@AuthenticationPrincipal MyMemberDetail principal){
        System.out.println("로그인 사용자 아이디: " + principal.getUsername());
        return "main";
    }

    @GetMapping(value = "/{id}")
    public Optional<Member> findOne(@PathVariable Long id) {
        return boardRep.findById(id);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        boardRep.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity userAccess(Model model, Authentication authentication) {
        MyMemberDetail userDetail = (MyMemberDetail)authentication.getPrincipal();
        log.info(userDetail.getUsername());
        model.addAttribute("info", userDetail.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
