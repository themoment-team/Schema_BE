package com.backend.schemabackend.auth;

import com.backend.schemabackend.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Setter
@Getter
public class MyMemberDetail implements UserDetails {
    private Member member;

    public MyMemberDetail(Member member){
        this.member = member;
    }

    @Override
    public String getUsername() {
        return member.getUserid();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않은지를 확인(true = 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    // 비밀번호가 만료되지 않았는지를 확인(true = 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    // 계정 활성화(사용 가능)가 되어있는지를 확인(true = 활성화)
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}