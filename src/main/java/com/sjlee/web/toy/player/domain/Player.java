package com.sjlee.web.toy.player.domain;

import com.sjlee.web.toy.match.domain.MatchStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                // PK

    @Column(name = "team_id", nullable = false)
    private Long teamId;            // 팀 ID, 논리FK
    @Column(name = "member_id")
    private Long memberId;          // 회원 ID

    @Column(name = "name")
    private String name;            // 이름

    @Column(name = "birth_date")
    private LocalDate birthDate;    // 생일

    @Column(name = "position")
    private String position;        // 포지션

    @Column(name = "back_number")
    private Integer backNumber;     // 등번호

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PlayerStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_type")
    private PlayerType playerType;

    @Column(name = "last_attended_at")
    private LocalDate lastAttendedAt;

    @Column(name = "join_at")
    private LocalDate joinAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "use_yn", nullable = false)
    private String useYn;

    // ===== 생성 시 기본값 세팅 =====
    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = (this.status == null) ? PlayerStatus.ACTIVE : this.status;
        this.playerType = (this.playerType == null) ? PlayerType.REGULAR : this.playerType;
        this.useYn = (this.useYn == null) ? "Y" : this.useYn;
    }

    public void update(String playerName, LocalDate birthDate, String position, Integer backNumber) {
        this.name = playerName;
        this.birthDate = birthDate;
        this.position = position;
        this.backNumber = backNumber;
    }
}
