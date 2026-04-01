package com.sjlee.web.toy.match.service;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.domain.MatchResult;
import com.sjlee.web.toy.match.domain.MatchStatus;
import com.sjlee.web.toy.match.dto.MatchDetail;
import com.sjlee.web.toy.match.dto.MatchList;
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
    public Page<MatchList> getMatchList(Pageable pageable) {
        return matchRepository.findMatchList(pageable);
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
     * 경기 상세 조회
     */
    public MatchDetail getMatchDetail(Long matchId) {
        return matchRepository.findMatchDetail(matchId);
    }

}
