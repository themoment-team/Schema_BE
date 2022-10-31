package com.backend.schemabackend.service;

import com.backend.schemabackend.auth.MyMemberDetail;
import com.backend.schemabackend.dto.InfoDto;
import com.backend.schemabackend.entity.Member;
import com.backend.schemabackend.repository.BoardRepository;
<<<<<<< HEAD

=======
>>>>>>> 2ef1925c4aaae6c4f8606c166a43b6b05a985f14
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {
    @Autowired
    private final BoardRepository boardRepository;

<<<<<<< HEAD
    public HashMap<String, Object> usernameOverlap(String userid) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", boardRepository.existsByUserid(userid));
        return map;
    }

    @Transactional
    public Optional<Member> checkUseridDuplicate(Member member){
	    return boardRepository.findByUserid(member.getUserid());
    }

    //닉네임 중복 검사
    public HashMap<String, Object> nicknameOverlap(String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("result", boardRepository.existsByPassword(password));
        return map;
    }
=======
    private BCryptPasswordEncoder bCryptPasswordEncoder;

>>>>>>> 2ef1925c4aaae6c4f8606c166a43b6b05a985f14

    @Transactional
    public void SignUp(Member member){
        boardRepository.save(member);
        member.setRole("USER");
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

    @Transactional
    public Member userModify(Member member){
        Member persistance = boardRepository.findByUserid(member.getUserid()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });

        persistance.setName(member.getName());
        persistance.setSchool(member.getSchool());
        persistance.setGrade(member.getGrade());
        persistance.setClass1(member.getClass1());

        return persistance;
    }

    @Transactional
    public Optional<Member> checkUseridDuplicate(Member member){
        return boardRepository.findByUserid(member.getUserid());
    }
    @Transactional
    public void logout(HttpSession session){
        session.invalidate();
    }

    @Transactional
    public List<InfoDto> info(){
        Sort sort = Sort.by(Sort.Direction.DESC,"name", "school", "grade", "class1");
        List<Member> list = boardRepository.findAll(sort);
        return list.stream().map(InfoDto::new).collect(Collectors.toList());
    }

}