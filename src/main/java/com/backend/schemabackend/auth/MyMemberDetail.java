package com.backend.schemabackend.auth;

import com.backend.schemabackend.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyMemberDetail implements UserDetails {
    private String userid;
    private String password;
    private String name;
    private String school;
    private String grade;
    private String class1;
    private String auth;

    public MyMemberDetail(Member member) {
        this.userid = member.getUserid();
        this.password = member.getPassword();
        this.name = member.getName();
        this.school = member.getSchool();
        this.grade = member.getGrade();
        this.class1 = member.getClass1();
        this.auth = "ROLE_" + member.getRole();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
    }

    @Override
    public String getUsername() {
        return this.userid;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public String getSchool(){
        return this.school;
    }

    public String getGrade(){
        return this.grade;
    }

    public String getClass1(){
        return this.class1;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}