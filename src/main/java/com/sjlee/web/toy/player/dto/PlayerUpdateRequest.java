package com.sjlee.web.toy.player.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlayerUpdateRequest {
    private String playerName;
    private LocalDate birthDate;
    private String position;
    private Integer backNumber;
}
