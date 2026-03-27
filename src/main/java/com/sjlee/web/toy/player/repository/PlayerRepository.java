package com.sjlee.web.toy.player.repository;

import com.sjlee.web.toy.player.domain.Player;
import com.sjlee.web.toy.player.dto.PlayerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("""
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
             p.name
         order by count(a.id) desc
    """)
    List<PlayerList> findPlayerList();
}
