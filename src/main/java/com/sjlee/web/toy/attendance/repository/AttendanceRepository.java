package com.sjlee.web.toy.attendance.repository;

import com.sjlee.web.toy.attendance.domain.Attendance;
import com.sjlee.web.toy.attendance.dto.AttendanceList;
import com.sjlee.web.toy.match.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query("""
    select new com.sjlee.web.toy.attendance.dto.AttendanceList(
        p.id,
        p.name,
        coalesce(a.attendStatus, 'ABSENT'),
        m.id
    )
    from Match m
    join Player p
        on p.teamId = m.teamId
    left join Attendance a
        on a.matchId = m.id
        and a.playerId = p.id
    where m.id = :matchId
    order by a.attendStatus, p.name
    
""")
    List<AttendanceList> findAttendanceByMatchId(Long matchId);

    Optional<Attendance> findByMatchIdAndPlayerId(Long matchId, Long playerId);
}
