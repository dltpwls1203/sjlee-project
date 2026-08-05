package com.sjlee.web.toy.opponentTeam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpponentTeamList {

    private Long id;                    // 상대팀 id
    private String name;                // 상대팀 명
    private String skillLevel;          // 상대팀 실력
    private String ageRange;            // 나이대

    private Long matchCount;            // 매치 횟수
    private Long winCount;              // 승리 횟수
    private Long drawCount;             // 무승부 횟수
    private Long loseCount;             // 패배 횟수

}
