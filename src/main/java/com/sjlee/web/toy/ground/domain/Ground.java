package com.sjlee.web.toy.ground.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
@Table(name = "ground")
public class Ground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 구장 ID (PK)

    @Column(nullable = false, length = 100)
    private String name;    // 구장 이름

    @Column(length = 200)
    private String location;    // 위치 (주소/지역)

    @Column(name = "field_width")
    private Integer fieldWidth;     // 경기장 너비 (m)

    @Column(name = "field_length")
    private Integer fieldLength;    // 경기장 길이 (m)

    @Column(name = "players_per_team")
    private Integer playersPerTeam; // 팀당 인원수

    @Column(name = "rental_fee")
    private Integer rentalFee;      // 구장 대여비

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성일

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 수정일

    @Column(name = "use_yn")
    private String useYn; // 수정일

    /* =====================
     * Lifecycle Callbacks
     * ===================== */
    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.useYn = (this.useYn == null) ? "Y" : this.useYn;
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
