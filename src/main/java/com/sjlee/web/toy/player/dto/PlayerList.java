package com.sjlee.web.toy.player.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlayerList {

    private final Long playerId;            // 선수 ID
    private final String name;              // 선수 이름
    private final LocalDate birthDate;      // 생년월일
    private final String position;          // 포지션
    private final Integer backNumber;       // 등번호
    private final Long attendCount;      // 출석 횟수



}
