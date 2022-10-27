package com.backend.schemabackend.service;

import com.backend.schemabackend.auth.MyMemberDetail;
import com.backend.schemabackend.auth.SecurityConfig;
import com.backend.schemabackend.entity.Member;
import com.backend.schemabackend.repository.BoardRepository;
import com.backend.schemabackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {
    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final MemberRepository repository;

    @Transactional
    public void joinMember(Member member){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        repository.saveMember(member);
    }

    public HashMap<String, Object> usernameOverlap(String userid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", boardRepository.existsByUserid(userid));
        return map;
    }

    //닉네임 중복 검사
    public HashMap<String, Object> nicknameOverlap(String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", boardRepository.existsByPassword(password));
        return map;
    }

    @Transactional
    public void SignUp(Member member){
        boardRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member SignIn(Member member){
        return boardRepository.findByUseridAndPassword(member.getUserid(), member.getPassword());
    }

    public UserDetails loadUserByUsername(String userid)throws UsernameNotFoundException {
        Member principal=boardRepository.findByUserid(userid)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당사용자를 찾을 수 없습니다." + userid);
                });
        return new MyMemberDetail(principal);
    }

}
