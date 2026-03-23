package com.sjlee.web.toy.attendance.repository;

import com.sjlee.web.toy.attendance.domain.Attendance;
import com.sjlee.web.toy.attendance.dto.AttendanceList;
import com.sjlee.web.toy.match.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query("""
    select new com.sjlee.web.toy.attendance.dto.AttendanceList(
        a.id,
        a.attendStatus
    )
    from Attendance a
    join Match m on m.id = a.matchId
    where a.matchId = :matchId
""")
    List<AttendanceList> findAttendanceByMatchId(Long matchId);
}
