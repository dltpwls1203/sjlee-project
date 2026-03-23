package com.sjlee.web.toy.match.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 매치 아이디

    @Column(name = "team_id", nullable = false)
    private Long teamId;    // 팀 아이디 (논리 FK)

    @Column(name = "opponent_team_id")
    private Long opponentTeamId;    // 상대팀 아이디 (논리 FK)

    @Column(name = "ground_id")
    private Long groundId;    // 경기장 아이디 (논리 FK)

    @Column(name = "match_at", nullable = false)
    private LocalDateTime matchAt;   // 매치 일시

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MatchStatus status;    // SCHEDULED / DONE / CANCELED / POSTPONE

    @Column(name = "our_score", nullable = false)
    private Integer ourScore; // 우리팀 점수

    @Column(name = "opponent_score", nullable = false)
    private Integer opponentScore; // 상대팀 점수

    @Enumerated(EnumType.STRING)
    @Column(name = "result", length = 10)
    private MatchResult result; // 상대팀 점수

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_type", nullable = false, length = 20)
    private MatchType matchType;


    // ===== 생성 시 기본값 세팅 =====
    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = (this.status == null) ? MatchStatus.DONE : this.status;
        this.ourScore = (this.ourScore == null) ? 0 : this.ourScore;
        this.opponentScore = (this.opponentScore == null) ? 0 : this.opponentScore;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
