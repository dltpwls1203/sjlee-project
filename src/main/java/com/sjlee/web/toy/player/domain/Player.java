package com.sjlee.web.toy.player.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // PK

    private Long teamId;            // 팀 ID
    private Long memberId;          // 회원 ID

    private String name;
    private LocalDate birthDate;
    private String position;
    private Integer backNumber;
    private PlayerStatus status;
    private PlayerType playerType;
    private LocalDate lastAttendedAt;
    private LocalDate joinAt;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String useYn;

}
