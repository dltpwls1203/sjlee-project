package com.sjlee.web.toy.player.service;

import com.sjlee.web.toy.match.domain.Match;
import com.sjlee.web.toy.player.domain.Player;
import com.sjlee.web.toy.player.dto.PlayerDetail;
import com.sjlee.web.toy.player.dto.PlayerList;
import com.sjlee.web.toy.player.dto.PlayerSearchCondition;
import com.sjlee.web.toy.player.dto.PlayerUpdateRequest;
import com.sjlee.web.toy.player.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;

    public Page<PlayerList> getPlayerList(PlayerSearchCondition condition, Pageable pageable) {
        return playerRepository.findPlayerList(condition, pageable);
    }

    /**
     * 경기 등록
     */
    @Transactional
    public Long createPlayer(Player player) {
        return playerRepository.save(player).getId();
    }

    public PlayerDetail getPlayerDetail(Long playerId) {

        Player player = playerRepository.findById(playerId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 선수입니다."));

        return new PlayerDetail(
                player.getId(),
                player.getName(),
                player.getBirthDate(),
                player.getPosition(),
                player.getBackNumber()
        );
    }

    @Transactional
    public void updatePlayer(Long playerId, PlayerUpdateRequest request) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 선수입니다."));

        player.update(
                request.getPlayerName(),
                request.getBirthDate(),
                request.getPosition(),
                request.getBackNumber()
        );

    }
}
