package com.backend.schemabackend.service;

import com.backend.schemabackend.auth.MyMemberDetail;
import com.backend.schemabackend.entity.Member;
import com.backend.schemabackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {
    private final MemberRepository repository;

    @Transactional
    public void joinMember(Member member){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        repository.saveMember(member);
    }

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        log.info(userid);
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        Member member = repository.findUserByUserid(userid);
        return new MyMemberDetail(member);
    }
}
