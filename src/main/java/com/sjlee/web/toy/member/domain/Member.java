package com.sjlee.web.toy.member.domain;
import com.sjlee.web.toy.member.dto.MemberCreateRequest;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // PK

    private String userId;              // UNIQUE
    private String userPw;
    private String userNm;
    private LocalDate birthDate;
    private String userPhone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    @Enumerated(EnumType.STRING)// enum
    private MemberStatus status;        // enum

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String createdId;
    private String updatedId;

    private LocalDateTime withdrawnAt;

    private int loginFailCnt;
    private boolean locked;
    private LocalDateTime lockedAt;

    public static Member create(MemberCreateRequest request) {
        Member member = new Member();

        member.userId = request.getUserId();
        member.userPw = request.getPassword();   // 암호화는 Service에서
        member.userNm = request.getUserNm();
        member.birthDate = request.getBirthDate();
        member.userPhone = request.getUserPhone();

        member.role = MemberRole.USER;            // 기본 권한
        member.status = MemberStatus.ACTIVE;      // 가입 시 ACTIVE

        member.createdAt = LocalDateTime.now();
        member.updatedAt = LocalDateTime.now();

        member.loginFailCnt = 0;
        member.locked = false;
        member.lockedAt = null;
        member.withdrawnAt = null;

        return member;
    }
}
