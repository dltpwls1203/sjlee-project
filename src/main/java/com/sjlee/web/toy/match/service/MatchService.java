package com.sjlee.web.toy.match.service;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.match.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
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
    public List<MatchList> getMatchList() {
        return matchRepository.findMatchList();
    }
    /**
     * 경기 등록
     */
    @Transactional
    public Long createMatch(Match match) {
        return matchRepository.save(match).getId();
    }
}
