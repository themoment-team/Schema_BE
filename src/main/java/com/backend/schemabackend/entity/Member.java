package com.backend.schemabackend.entity;

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
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
