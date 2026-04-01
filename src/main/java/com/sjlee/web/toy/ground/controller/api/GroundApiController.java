package com.sjlee.web.toy.ground.controller.api;

import com.sjlee.web.toy.common.response.ApiResponse;
import com.sjlee.web.toy.ground.domain.Ground;
import com.sjlee.web.toy.ground.dto.GroundList;
import com.sjlee.web.toy.ground.dto.GroundSelect;
import com.sjlee.web.toy.ground.service.GroundService;
import com.sjlee.web.toy.match.dto.MatchList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grounds")
public class GroundApiController {

    private final GroundService groundService;

    /**
     * 구장 목록 조회
     */
    @GetMapping
    public ApiResponse<List<GroundList>> getGroundList() {
        List<GroundList> grounds = groundService.getGroundList();
        return ApiResponse.success(grounds);
    }

    /**
     * 경기 등록 화면 > 구장 선택 목록 조회
     */
    @GetMapping("/select")
    public ApiResponse<List<GroundSelect>> getGroundSelectList() {
        return ApiResponse.success(
                groundService.getGroundSelectItems()
        );
    }

    /**
     * 구장 등록
     */
    @PostMapping
    public ApiResponse<Long> createGround(@RequestBody Ground ground) {
        Long groundId = groundService.createGround(ground);
        return ApiResponse.success(groundId);
    }
}
