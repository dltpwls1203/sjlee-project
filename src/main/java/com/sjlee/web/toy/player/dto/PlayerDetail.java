package com.sjlee.web.toy.player.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PlayerDetail {

    private Long playerId;
    private String playerName;
    private LocalDate birthDate;
    private String position;
    private Integer backNumber;

}
