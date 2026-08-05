package com.sjlee.web.toy.player.controller.api;

import com.sjlee.web.toy.global.response.ApiResponse;
import com.sjlee.web.toy.global.response.PageResponse;
import com.sjlee.web.toy.match.dto.MatchDetail;
import com.sjlee.web.toy.match.dto.MatchSearchCondition;
import com.sjlee.web.toy.match.dto.MatchUpdateRequest;
import com.sjlee.web.toy.player.domain.Player;
import com.sjlee.web.toy.player.dto.PlayerDetail;
import com.sjlee.web.toy.player.dto.PlayerList;
import com.sjlee.web.toy.player.dto.PlayerSearchCondition;
import com.sjlee.web.toy.player.dto.PlayerUpdateRequest;
import com.sjlee.web.toy.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerApiController {

    private final PlayerService playerService;

    /**
     * 선수 목록 조회
     */
    @GetMapping
    public ApiResponse<PageResponse<PlayerList>> getPlayerList(PlayerSearchCondition condition, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<PlayerList> players = playerService.getPlayerList(condition, pageable);
        return ApiResponse.success(new PageResponse<>(players));
    }

    /**
     * 선수 등록
     */
    @PostMapping
    public ApiResponse<Long> createPlayer(@RequestBody Player player) {
        Long playerId = playerService.createPlayer(player);
        return ApiResponse.success(playerId);
    }


    /**
     * 경기 상세 정보
     */
    @GetMapping("/{playerId}")
    public ApiResponse<PlayerDetail> playerDetail(@PathVariable Long playerId) {
        return ApiResponse.success(playerService.getPlayerDetail(playerId));
    }

    /**
     * 선수 수정
     */
    @PutMapping("/{playerId}")
    public ApiResponse<Long> updateMatch(
            @PathVariable Long playerId,
            @RequestBody PlayerUpdateRequest request) {

        playerService.updatePlayer(playerId, request);
        return ApiResponse.success(playerId);
    }
}
