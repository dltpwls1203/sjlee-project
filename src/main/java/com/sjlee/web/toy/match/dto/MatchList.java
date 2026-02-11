package com.sjlee.web.toy.match.dto;

import com.sjlee.web.toy.match.domain.MatchResult;
import com.sjlee.web.toy.match.domain.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MatchList {
    private final Long matchId;             // 경기 ID
    private final LocalDateTime matchAt;    // 경기 일시
    private final String groundName;        // 구장 명
    private final Integer playersPerTeam;   // 경기 인원
    private final String opponentTeamName;  // 상대팀 명
    private final Integer ourScore;         // 우리팀 점수
    private final Integer opponentScore;    // 상대팀 점수
    private final MatchResult result;       // 경기 결과
    private final MatchStatus status;       // 경기 상태 (SCHEDULED / DONE / CANCELED / POSTPONE)
    private final Long attendanceCount;         // 출석 인원 수 (ATTEND + LATE)

}
