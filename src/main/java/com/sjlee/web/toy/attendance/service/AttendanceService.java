package com.sjlee.web.toy.attendance.service;

import com.sjlee.web.toy.attendance.dto.AttendanceList;
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
}
