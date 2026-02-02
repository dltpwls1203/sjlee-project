package com.sjlee.web.toy.match.repository;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.dto.MatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("""
        select new com.sjlee.web.toy.match.dto.MatchList(
            m.id,
            m.matchAt,
            ot.name,
            m.ourScore,
            m.opponentScore,
            m.status,
            count(a.id)
        )
        from Match m
        left join Attendance a
            on a.matchId = m.id
           and a.attendStatus in ('ATTEND', 'LATE')
        left join OpponentTeam ot
            on ot.id = m.opponentTeamId
        group by m.id, ot.name
        order by m.matchAt desc
    """)
    List<MatchList> findMatchList();
}
