package com.sjlee.web.toy.opponentTeam.repository;

import com.sjlee.web.toy.opponentTeam.domain.OpponentTeam;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamList;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpponentTeamRepository extends JpaRepository<OpponentTeam, Long> {

    @Query(
            value = """
        SELECT new com.sjlee.web.toy.opponentTeam.dto.OpponentTeamList(
            ot.id,
            ot.name,
            ot.skillLevel,
            ot.ageRange,
            COUNT(m.id),
            SUM(CASE WHEN m.result = 'WIN' THEN 1 ELSE 0 END),
            SUM(CASE WHEN m.result = 'DRAW' THEN 1 ELSE 0 END),
            SUM(CASE WHEN m.result = 'LOSE' THEN 1 ELSE 0 END)
        )
        FROM OpponentTeam ot
        LEFT JOIN Match m ON m.opponentTeamId = ot.id
        GROUP BY ot.id, ot.name, ot.skillLevel, ot.ageRange
    """,
            countQuery = """
        SELECT COUNT(ot.id)
        FROM OpponentTeam ot
    """
    )
    Page<OpponentTeamList> findOpponentTeamList(Pageable pageable);

    @Query("""
    select new com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect(
        ot.id,
        ot.name
    )
    from OpponentTeam ot
    order by ot.name
""")
    List<OpponentTeamSelect> findSelectItems();
}
