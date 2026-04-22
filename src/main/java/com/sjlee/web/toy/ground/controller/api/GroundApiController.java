package com.sjlee.web.toy.ground.controller.api;

import com.sjlee.web.toy.global.response.ApiResponse;
import com.sjlee.web.toy.global.response.PageResponse;
import com.sjlee.web.toy.ground.domain.Ground;
import com.sjlee.web.toy.ground.dto.GroundList;
import com.sjlee.web.toy.ground.dto.GroundSelect;
import com.sjlee.web.toy.ground.service.GroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ApiResponse<PageResponse<GroundList>> getGroundList(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<GroundList> grounds = groundService.getGroundList(pageable);
        return ApiResponse.success(new PageResponse<>(grounds));
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
