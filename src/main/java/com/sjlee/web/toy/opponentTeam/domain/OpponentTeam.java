package com.sjlee.web.toy.opponentTeam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
@Table(name = "opponent_team")
public class OpponentTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 상대팀 아이디

    @Column(nullable = false, length = 100)
    private String name;    // 상대팀 명

    @Column(name = "skill_level", length = 20)
    private String skillLevel;     // 체감 실력 (B-/C+ 등)

    @Column(name = "age_range", length = 200)
    private String ageRange;        // 나이대

    @Column(length = 200)
    private String note;            // 비고

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "use_yn", nullable = false, length = 1)
    private String useYn;


    // ===== 생성 시 기본값 세팅 =====
    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.useYn = "Y";
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void deactivate() {
        this.useYn = "N";
    }
}
