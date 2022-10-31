package com.backend.schemabackend.dto;

import com.backend.schemabackend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InfoDto {
    private String name;
    private String school;
    private String grade;
    private String class1;

    public InfoDto(Member member){
        this.name = member.getName();
        this.school = member.getSchool();
        this.grade = member.getGrade();
        this.class1 = member.getClass1();
    }
}
