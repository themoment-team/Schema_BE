package com.backend.schemabackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {
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
}
