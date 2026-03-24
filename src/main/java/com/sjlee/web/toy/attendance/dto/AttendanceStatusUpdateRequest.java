package com.sjlee.web.toy.attendance.dto;

import com.sjlee.web.toy.attendance.domain.AttendStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AttendanceStatusUpdateRequest {
    private List<Long> playerIds;
    private AttendStatus attendStatus;
}
