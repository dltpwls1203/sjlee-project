package com.sjlee.web.toy.match.service;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.domain.MatchResult;
import com.sjlee.web.toy.match.domain.MatchStatus;
import com.sjlee.web.toy.match.dto.MatchDetail;
import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.match.dto.MatchSearchCondition;
import com.sjlee.web.toy.match.dto.MatchUpdateRequest;
import com.sjlee.web.toy.match.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchService {

    private final MatchRepository matchRepository;

    /**
     * 경기 목록 조회
     */
    public Page<MatchList> getMatchList(MatchSearchCondition condition, Pageable pageable) {
        return matchRepository.findMatchList(condition, pageable);
    }

    /**
     * 경기 등록
     */
    @Transactional
    public Long createMatch(Match match) {
        // 경기 점수에 따른 result 값 설정
        match.calculateResult();
        return matchRepository.save(match).getId();
    }

    /**
     * 경기 수정
     */
    @Transactional
    public void updateMatch(Long matchId, MatchUpdateRequest request) {

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 경기입니다."));

        match.update(
                request.getMatchAt(),
                request.getGroundId(),
                request.getOpponentTeamId(),
                request.getStatus(),
                request.getOurScore(),
                request.getOpponentScore()
        );
    }


    /**
     * 경기 상세 조회
     */
    public MatchDetail getMatchDetail(Long matchId) {
        return matchRepository.findMatchDetail(matchId);
    }

}
