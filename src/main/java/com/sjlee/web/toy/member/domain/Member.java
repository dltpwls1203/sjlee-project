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
    private Long id;                // PK

    @Column(nullable = false, unique = true)
    private String email;           // 로그인 ID

    private String password;        // LOCAL 로그인만 사용 (소셜은 null 가능)

    private String name;
    private LocalDate birthDate;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime withdrawnAt;

    private int loginFailCnt;
    private boolean locked;
    private LocalDateTime lockedAt;

    protected Member() {}

    public static Member create(MemberCreateRequest request) {
        Member member = new Member();

        member.email = request.getEmail();
        member.password = request.getPassword();    // 암호화는 Service에서
        member.name = request.getName();
        member.birthDate = request.getBirthDate();
        member.phone = request.getPhone();

        member.role = MemberRole.USER;
        member.status = MemberStatus.ACTIVE;

        member.createdAt = LocalDateTime.now();
        member.updatedAt = LocalDateTime.now();

        member.loginFailCnt = 0;
        member.locked = false;
        member.lockedAt = null;
        member.withdrawnAt = null;

        return member;
    }

    // 로그인 실패 처리
    public void increaseLoginFailCnt(int maxFailCnt) {
        this.loginFailCnt++;

        if (this.loginFailCnt >= maxFailCnt) {
            this.locked = true;
        }
    }

    // 로그인 성공 처리
    public void resetLoginFailCnt() {
        this.loginFailCnt = 0;
        this.locked = false;
    }
}
