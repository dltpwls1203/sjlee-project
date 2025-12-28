package com.sjlee.web.toy.member.repository;

import com.sjlee.web.toy.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findByUserNm(String userNm);
    boolean existsByUserId(String userId);
}
