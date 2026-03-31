package com.sjlee.web.toy.player.service;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.player.domain.Player;
import com.sjlee.web.toy.player.dto.PlayerList;
import com.sjlee.web.toy.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;

    public List<PlayerList> getPlayerList() {
        return playerRepository.findPlayerList();
    }

    /**
     * 경기 등록
     */
    @Transactional
    public Long createPlayer(Player player) {
        return playerRepository.save(player).getId();
    }

}
