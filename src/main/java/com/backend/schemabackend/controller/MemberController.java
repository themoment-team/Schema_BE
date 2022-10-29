package com.backend.schemabackend.controller;


import com.backend.schemabackend.auth.MyMemberDetail;
import com.backend.schemabackend.dto.ResponseDto;
import com.backend.schemabackend.entity.Member;
import com.backend.schemabackend.repository.BoardRepository;
import com.backend.schemabackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@ResponseBody
@Slf4j
public class MemberController {

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
    public ResponseDto<Integer> login(@RequestBody Member member, HttpServletRequest request){
        System.out.println("MemberController : login 함수 호출됨");
        Member principal = memberService.SignIn(member);
        HttpSession session = request.getSession();
        if(principal!=null){
            session.setAttribute("principal",principal);
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        else
            return new ResponseDto<Integer>(HttpStatus.NOT_FOUND.value(),1);
    }

    @PostMapping("/modify")
    public ResponseDto<Integer> modify(@RequestBody Member member, HttpServletRequest request){
        HttpSession session = request.getSession();
        Member modifyMember = memberService.userModify(member);

        session.setAttribute("user", modifyMember);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}