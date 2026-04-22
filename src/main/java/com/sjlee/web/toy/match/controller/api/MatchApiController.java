package com.sjlee.web.toy.match.controller.api;

import com.sjlee.web.toy.attendance.dto.AttendanceList;
import com.sjlee.web.toy.attendance.dto.AttendanceStatusUpdateRequest;
import com.sjlee.web.toy.attendance.service.AttendanceService;
import com.sjlee.web.toy.global.response.ApiResponse;
import com.sjlee.web.toy.global.response.PageResponse;
import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.dto.MatchDetail;
import com.sjlee.web.toy.match.dto.MatchList;
import com.sjlee.web.toy.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchApiController {

    private final MatchService matchService;
    private final AttendanceService attendanceService;

    /**
     * 경기 목록 조회
     */
    @GetMapping
    public ApiResponse<PageResponse<MatchList>> getMatchList(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<MatchList> matches = matchService.getMatchList(pageable);
        return ApiResponse.success(new PageResponse<>(matches));
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

    @GetMapping("/{matchId}/attendance")
    public ApiResponse<List<AttendanceList>> getAttendanceList(
            @PathVariable Long matchId) {

        return ApiResponse.success(
                attendanceService.getAttendanceList(matchId)
        );
    }

    /**
     * 경기별 출석 상태 일괄 변경
     */
    @PutMapping("/{matchId}/attendance")
    public ApiResponse<Void> updateAttendanceStatus(
            @PathVariable Long matchId,
            @RequestBody AttendanceStatusUpdateRequest request
    ) {
        attendanceService.updateAttendanceStatus(matchId, request);
        return ApiResponse.success(null);
    }



}
