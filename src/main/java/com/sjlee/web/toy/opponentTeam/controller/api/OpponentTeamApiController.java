package com.sjlee.web.toy.opponentTeam.controller.api;

import com.sjlee.web.toy.common.response.ApiResponse;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;
import com.sjlee.web.toy.opponentTeam.service.OpponentTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ApiResponse<List<OpponentTeamSelect>> getOpponentTeams() {
        return ApiResponse.success(opponentTeamService.getOpponentTeams());
    }

}
