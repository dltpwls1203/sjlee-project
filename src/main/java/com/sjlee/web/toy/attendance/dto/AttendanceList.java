package com.sjlee.web.toy.attendance.dto;

import com.sjlee.web.toy.attendance.domain.AttendStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttendanceList {
    private Long playerId;
    private String playerName;
    private AttendStatus attendStatus;
    private Long matchId;
}
