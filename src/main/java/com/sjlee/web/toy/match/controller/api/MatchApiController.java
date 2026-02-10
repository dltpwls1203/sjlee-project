package com.sjlee.web.toy.match.controller.api;

import com.sjlee.web.toy.common.response.ApiResponse;
import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.dto.MatchDetail;
import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchApiController {

    private final MatchService matchService;

    /**
     * 경기 목록 조회
     */
    @GetMapping
    public ApiResponse<List<MatchList>> getMatchList() {
        return ApiResponse.success(matchService.getMatchList());
    }

    /**
     * 경기 생성
     */
    @PostMapping
    public ApiResponse<Long> createMatch(@RequestBody Match match) {
        Long matchId = matchService.createMatch(match);
        return ApiResponse.success(matchId);
    }

    /**
     * 관리자 경기 상세 정보
     */
    @GetMapping("/{matchId}")
    public ApiResponse<MatchDetail> matchDetail(@PathVariable Long matchId) {
        return ApiResponse.success(matchService.getMatchDetail(matchId));
    }

}
