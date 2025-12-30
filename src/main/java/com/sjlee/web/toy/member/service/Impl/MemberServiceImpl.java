package com.sjlee.web.toy.member.service.Impl;

import com.sjlee.web.toy.common.exception.DuplicateUserIdException;
import com.sjlee.web.toy.member.domain.Member;
import com.sjlee.web.toy.member.dto.LoginRequest;
import com.sjlee.web.toy.member.dto.MemberCreateRequest;
import com.sjlee.web.toy.member.dto.MemberResponse;
import com.sjlee.web.toy.member.exception.LoginFailException;
import com.sjlee.web.toy.member.repository.MemberRepository;
import com.sjlee.web.toy.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse registerMember(MemberCreateRequest request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateUserIdException();
        }

        Member member = Member.create(request);

        memberRepository.save(member);

        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .status(member.getStatus().name())
                .build();
    }

    @Override
    public Member login(LoginRequest request) {

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(LoginFailException::new);

        if (member.isLocked()) {
            throw new LoginFailException("계정이 잠겨 있습니다.");
        }

        resetLoginFailCnt(member);

        return member;
    }


    private void resetLoginFailCnt(Member member) {
       // member.resetLoginFailCnt();
    }
}
