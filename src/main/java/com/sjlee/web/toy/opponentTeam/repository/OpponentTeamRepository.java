package com.sjlee.web.toy.opponentTeam.repository;

import com.sjlee.web.toy.opponentTeam.domain.OpponentTeam;
import com.sjlee.web.toy.opponentTeam.dto.OpponentTeamSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpponentTeamRepository extends JpaRepository<OpponentTeam, Long> {
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
