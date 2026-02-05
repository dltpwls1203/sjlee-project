package com.sjlee.web.toy.ground.controller.api;

import com.sjlee.web.toy.common.response.ApiResponse;
import com.sjlee.web.toy.ground.dto.GroundSelect;
import com.sjlee.web.toy.ground.service.GroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grounds")
public class GroundApiController {

    private final GroundService groundService;

    /**
     * 경기 등록 화면 - 구장 선택 목록 조회
     */
    @GetMapping
    public ApiResponse<List<GroundSelect>> getGrounds() {
        return ApiResponse.success(
                groundService.getGroundSelectItems()
        );
    }
}
