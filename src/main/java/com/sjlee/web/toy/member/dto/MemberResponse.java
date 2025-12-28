package com.sjlee.web.toy.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponse {
    private Long id;
    private String userId;
    private String status;
}
