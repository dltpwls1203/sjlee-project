package com.sjlee.web.toy.opponentTeam.service;

import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;
import com.sjlee.web.toy.opponentTeam.repository.OpponentTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpponentTeamService {

    private final OpponentTeamRepository opponentTeamRepository;

    /**
     * 상대팀 목록 조회
     */
    public List<OpponentTeamSelect> getOpponentTeams() {
        return opponentTeamRepository.findSelectItems();
    }
}
