package com.sjlee.web.toy.attendance.service;

import com.sjlee.web.toy.attendance.domain.Attendance;
import com.sjlee.web.toy.attendance.dto.AttendanceList;
import com.sjlee.web.toy.attendance.dto.AttendanceStatusUpdateRequest;
import com.sjlee.web.toy.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public List<AttendanceList> getAttendanceList(Long matchId) {
        return attendanceRepository.findAttendanceByMatchId(matchId);
    }

    @Transactional
    public void updateAttendanceStatus(Long matchId, AttendanceStatusUpdateRequest request) {

        if (request.getPlayerIds() == null || request.getPlayerIds().isEmpty()) {
            throw new IllegalArgumentException("선수를 선택하세요.");
        }

        if (request.getAttendStatus() == null) {
            throw new IllegalArgumentException("출석 상태가 없습니다.");
        }

        for (Long playerId : request.getPlayerIds()) {
            Attendance attendance = attendanceRepository
                    .findByMatchIdAndPlayerId(matchId, playerId)
                    .orElseGet(() -> new Attendance(matchId, playerId));

            attendance.changeAttendStatus(request.getAttendStatus());


            attendanceRepository.save(attendance);
        }
    }
}
