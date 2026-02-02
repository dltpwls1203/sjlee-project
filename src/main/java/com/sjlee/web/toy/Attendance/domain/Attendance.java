package com.sjlee.web.toy.Attendance.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 출석 아이디

    @Column(name = "match_id", nullable = false)
    private Long matchId;    // 시합 아이디 (논리 FK)

    @Column(name = "player_id", nullable = false)
    private Long playerId;   // 선수 아이디 (논리 FK)

    @Column(name = "attend_status", nullable = false, length = 20)
    private String attendStatus; // ATTEND / LATE / ABSENT / UNKNOWN

    @Column(length = 200)
    private String note;     // 비고

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===== 생성 시 기본값 세팅 =====
    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.attendStatus = (this.attendStatus == null) ? "UNKNOWN" : this.attendStatus;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
