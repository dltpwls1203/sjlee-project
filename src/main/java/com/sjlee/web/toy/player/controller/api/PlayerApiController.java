package com.sjlee.web.toy.player.controller.api;

import com.sjlee.web.toy.common.response.ApiResponse;
import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.match.service.MatchService;
import com.sjlee.web.toy.player.dto.PlayerList;
import com.sjlee.web.toy.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerApiController {

    private final PlayerService playerService;

    /**
     * 선수 목록 조회
     */
    @GetMapping
    public ApiResponse<List<PlayerList>> getPlayerList() {
        return ApiResponse.success(playerService.getPlayerList());
    }
}
