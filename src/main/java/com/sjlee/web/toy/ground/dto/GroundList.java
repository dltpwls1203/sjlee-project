package com.sjlee.web.toy.ground.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroundList {
    private final Long groundId;            // 구장 ID
    private final String name;              // 구장 이름
    private final Integer fieldWidth;      // 구장 크기
    private final Integer fieldLength;     // 구장 길이
    private final Integer playerPerTeam;    // 구장 형태 (N vs N)
    private final Integer rentalFee;        // 구장 대여료
}
