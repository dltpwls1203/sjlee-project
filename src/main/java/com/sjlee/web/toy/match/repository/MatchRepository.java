package com.sjlee.web.toy.match.repository;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.match.dto.MatchDetail;
import com.sjlee.web.toy.match.dto.MatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("""
        select new com.sjlee.web.toy.match.dto.MatchList(
            m.id,
            m.matchAt,
            g.name,
            g.playersPerTeam,
            ot.name,
            m.ourScore,
            m.opponentScore,
            m.result,
            m.status,
            count(a.id)
        )
        from Match m
        left join Attendance a
            on a.matchId = m.id
           and a.attendStatus in ('ATTEND', 'LATE')
        left join OpponentTeam ot
            on ot.id = m.opponentTeamId
        left join Ground g
            on g.id = m.groundId
        group by
            m.id,
            m.matchAt,
            ot.name,
            g.name,
            g.playersPerTeam,
            m.ourScore,
            m.opponentScore,
            m.result,
            m.status
        order by m.matchAt desc
    """)
    List<MatchList> findMatchList();

    @Query("""
        select new com.sjlee.web.toy.match.dto.MatchDetail(
            m.id,
            m.matchAt,
            g.name,
            g.playersPerTeam,
            ot.name,
            m.ourScore,
            m.opponentScore,
            m.result,
            m.status,
            count(a.id)
        )
        from Match m
        left join Ground g
            on g.id = m.groundId
        left join OpponentTeam ot
            on ot.id = m.opponentTeamId
        left join Attendance a
            on a.matchId = m.id
           and a.attendStatus in ('ATTEND', 'LATE')
        where m.id = :matchId
        group by
            m.id,
            m.matchAt,
            g.id,
            g.name,
            g.playersPerTeam,
            ot.id,
            ot.name,
            m.ourScore,
            m.opponentScore,
            m.result,
            m.status
    """)
    MatchDetail findMatchDetail(@Param("matchId") Long matchId);

}
