package com.sjlee.web.toy.opponentTeam.service;

import com.sjlee.web.toy.opponentTeam.domain.OpponentTeam;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamList;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;
import com.sjlee.web.toy.opponentTeam.repository.OpponentTeamRepository;
import com.sjlee.web.toy.player.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpponentTeamService {

    private final OpponentTeamRepository opponentTeamRepository;

    /**
     *  상대팀 목록 조회
     */
    public Page<OpponentTeamList> getOpponentTeamList(Pageable pageable) {
        return opponentTeamRepository.findOpponentTeamList(pageable);
    }

    /**
     * 상대팀 등록
     */
    @Transactional
    public Long createOpponentTeam(OpponentTeam opponentTeam) {
        return opponentTeamRepository.save(opponentTeam).getId();
    }

    /**
     *  경기 등록 화면 > 상대팀 목록 조회
     */
    public List<OpponentTeamSelect> getOpponentTeamSelectList() {
        return opponentTeamRepository.findSelectItems();
    }



}
