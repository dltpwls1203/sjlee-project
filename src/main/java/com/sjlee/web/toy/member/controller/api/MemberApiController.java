package com.sjlee.web.toy.member.controller.api;

import com.sjlee.web.toy.global.response.ApiResponse;
import com.sjlee.web.toy.member.domain.Member;
import com.sjlee.web.toy.member.dto.LoginRequest;
import com.sjlee.web.toy.member.dto.LoginResponse;
import com.sjlee.web.toy.member.dto.MemberCreateRequest;
import com.sjlee.web.toy.member.dto.MemberResponse;
import com.sjlee.web.toy.member.exception.LoginFailException;
import com.sjlee.web.toy.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
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

    public static final String LOGIN_MEMBER = "LOGIN_MEMBER";
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<ApiResponse<MemberResponse>> createMember (@RequestBody MemberCreateRequest request) {
        MemberResponse response = memberService.registerMember(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    // 로그인 체크 (API)
    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse<LoginResponse>> loginCheck(
            @RequestBody LoginRequest request,
            HttpSession session
    ) {
        try {
            Member member = memberService.login(request);
            session.setAttribute("LOGIN_MEMBER", member);

            LoginResponse response = new LoginResponse(member.getRole().name());

            return ResponseEntity.ok(ApiResponse.success(response));

        } catch (LoginFailException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail("LOGIN_FAIL", e.getMessage()));
        }
    }
}
