package com.sjlee.web.toy.player.repository;

import com.sjlee.web.toy.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
