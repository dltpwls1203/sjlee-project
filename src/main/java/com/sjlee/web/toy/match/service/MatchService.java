package com.sjlee.web.toy.match.service;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.dto.MatchList;

import java.util.List;

public interface MatchService {

    /**
     * 경기 목록 조회
     */
    List<MatchList> getMatchList();

    /**
     * 경기 등록
     */
    Long createMatch(Match match);
}
