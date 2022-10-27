package com.backend.schemabackend.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Data
public class Member extends TimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String userid;

    private String password;

    private String name;

    private String school;

    private String grade;

    private String class1;

    private String role;

    public Member() {
    }
    public Member(String userid, String password, String name, String school, String grade, String class1) {
        this.userid = userid;
        this.password = password;
        this.name = name;
        this.school = school;
        this.grade = grade;
        this.class1 = class1;
    }

    public void modify(String name, String school, String grade, String class1) {
        this.name = name;
        this.school = school;
        this.grade = grade;
        this.class1 = class1;
    }
}
