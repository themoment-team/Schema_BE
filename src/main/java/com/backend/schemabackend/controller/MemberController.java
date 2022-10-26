package com.backend.schemabackend.controller;


import com.backend.schemabackend.auth.MyMemberDetail;
import com.backend.schemabackend.entity.Member;
import com.backend.schemabackend.repository.BoardRepository;
import com.backend.schemabackend.repository.MemberRepository;
import com.backend.schemabackend.repository.MembersRepository;
import com.backend.schemabackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private MemberService memberService;
    @Autowired
    private BoardRepository boardRep;

    @Autowired
    public MemberController(BoardRepository boardRep) {
        this.boardRep = boardRep;
    }

    @GetMapping(value = "/{id}")
    public Optional<Member> findOne(@PathVariable Long id) {
        return boardRep.findById(id);
    }

    @PostMapping("/signUp")
    public ResponseEntity signUp(Member member) {
        member.setRole("USER");
        memberService.joinMember(member);
        log.info(member.getUserid());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public Member update(@PathVariable Long id, @RequestParam String userid, @RequestParam String password, @RequestParam String name, @RequestParam String school, @RequestParam String grade, @RequestParam String class1) {
        Optional<Member> board = boardRep.findById(id);
        board.get().setUserid(userid);
        board.get().setPassword(password);
        return boardRep.save(board.get());
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
