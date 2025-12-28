package com.sjlee.web.toy.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {
    private String userId;
    private String password;
    private String userNm;
    private LocalDate birthDate;
    private String userPhone;
}
