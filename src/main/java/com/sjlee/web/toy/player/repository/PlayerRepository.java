package com.sjlee.web.toy.player.repository;

import com.sjlee.web.toy.player.domain.Player;
import com.sjlee.web.toy.player.dto.PlayerList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query(
            value = """
        select new com.sjlee.web.toy.player.dto.PlayerList(
            p.id,
            p.name,
            p.birthDate,
            p.position,
            p.backNumber,
            count(a.id)
        )
        from Player p
        left join Attendance a
            on a.playerId = p.id
        group by
            p.id,
            p.name,
            p.birthDate,
            p.position,
            p.backNumber
        order by count(a.id) desc
    """,
            countQuery = """
        select count(p)
        from Player p
    """
    )
    Page<PlayerList> findPlayerList(Pageable pageable);
}
