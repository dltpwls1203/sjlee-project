package com.sjlee.web.toy.controller.api;

import com.sjlee.web.toy.common.response.ApiResponse;
import com.sjlee.web.toy.member.dto.MemberCreateRequest;
import com.sjlee.web.toy.member.dto.MemberResponse;
import com.sjlee.web.toy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<ApiResponse<MemberResponse>> createMember (@RequestBody MemberCreateRequest request) {
        MemberResponse response = memberService.registerMember(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }
}
