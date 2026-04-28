package com.sjlee.web.toy.opponentTeam.controller.api;

import com.sjlee.web.toy.global.response.ApiResponse;
import com.sjlee.web.toy.global.response.PageResponse;
import com.sjlee.web.toy.opponentTeam.domain.OpponentTeam;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamList;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;
import com.sjlee.web.toy.opponentTeam.service.OpponentTeamService;
import com.sjlee.web.toy.player.domain.Player;
import com.sjlee.web.toy.player.dto.PlayerList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opponent-teams")
@RequiredArgsConstructor
public class OpponentTeamApiController {

    private final OpponentTeamService opponentTeamService;

    /**
     * 상대팀 목록 조회
     */
    @GetMapping
    public ApiResponse<PageResponse<OpponentTeamList>> getOpponentTeamList(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<OpponentTeamList> opponentTeams = opponentTeamService.getOpponentTeamList(pageable);
        return ApiResponse.success(new PageResponse<>(opponentTeams));
    }

    /**
     * 선수 등록
     */
    @PostMapping
    public ApiResponse<Long> createOpponentTeam(@RequestBody OpponentTeam opponentTeam) {
        Long opponentTeamId = opponentTeamService.createOpponentTeam(opponentTeam);
        return ApiResponse.success(opponentTeamId);
    }

    /**
     * 경기 등록 화면 > 상대팀 목록 조회
     */
    @GetMapping("/select")
    public ApiResponse<List<OpponentTeamSelect>> getOpponentTeamSelectList() {
        return ApiResponse.success(opponentTeamService.getOpponentTeamSelectList());
    }

}
