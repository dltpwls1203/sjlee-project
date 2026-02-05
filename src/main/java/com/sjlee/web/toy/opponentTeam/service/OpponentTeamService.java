package com.sjlee.web.toy.opponentTeam.service;

import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;

import java.util.List;

public interface OpponentTeamService {

    /**
     * 상대팀 목록 조회
     */
    List<OpponentTeamSelect> getOpponentTeams();
}
