package com.sjlee.web.toy.match.dto;

import com.sjlee.web.toy.match.domain.MatchStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MatchUpdateRequest {

    private LocalDateTime matchAt;
    private Long groundId;
    private Long opponentTeamId;
    private MatchStatus status;
    private Integer ourScore;
    private Integer opponentScore;
}
