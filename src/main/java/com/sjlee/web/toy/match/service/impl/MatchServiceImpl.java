package com.sjlee.web.toy.match.service.impl;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.match.repository.MatchRepository;
import com.sjlee.web.toy.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Override
    public List<MatchList> getMatchList() {
        return matchRepository.findMatchList();
    }

    @Override
    @Transactional
    public Long createMatch(Match match) {
        return matchRepository.save(match).getId();
    }
}
