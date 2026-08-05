package com.sjlee.web.toy.match.dto;

import com.sjlee.web.toy.match.domain.MatchResult;
import com.sjlee.web.toy.match.domain.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MatchDetail {

    private Long matchId;               // 경기 ID
    private LocalDateTime matchAt;      // 경기 일시

    private Long groundId;              // 구장 ID
    private String groundName;          // 구장 명
    private Integer playersPerTeam;     // 경기 인원

    private Long opponentTeamId;        // 상대팀 ID
    private String opponentTeamName;    // 상대팀 명

    private Integer ourScore;           // 우리팀 점수
    private Integer opponentScore;      // 상대팀 점수

    private MatchResult result;         // 경기 결과
    private MatchStatus status;         // 경기 상태 (SCHEDULED / DONE / CANCELED / POSTPONE)

    private Long attendanceCount;           // 출석 인원 수 (ATTEND + LATE)

}
