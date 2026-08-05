package com.sjlee.web.toy.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {
    private String email;
    private String password;
    private String name;
    private LocalDate birthDate;
    private String phone;
}
