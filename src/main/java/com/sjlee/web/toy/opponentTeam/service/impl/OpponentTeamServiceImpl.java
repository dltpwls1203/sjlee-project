package com.sjlee.web.toy.opponentTeam.service.impl;


import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;
import com.sjlee.web.toy.opponentTeam.repository.OpponentTeamRepository;
import com.sjlee.web.toy.opponentTeam.service.OpponentTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpponentTeamServiceImpl implements OpponentTeamService {

    private final OpponentTeamRepository opponentTeamRepository;

    @Override
    public List<OpponentTeamSelect> getOpponentTeams() {
        return opponentTeamRepository.findSelectItems();
    }
}
