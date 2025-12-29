package com.sjlee.web.toy.member.service;

import com.sjlee.web.toy.common.exception.DuplicateUserIdException;
import com.sjlee.web.toy.member.domain.Member;
import com.sjlee.web.toy.member.dto.MemberCreateRequest;
import com.sjlee.web.toy.member.dto.MemberResponse;
import com.sjlee.web.toy.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponse registerMember(MemberCreateRequest request) {

        if (memberRepository.existsByUserId(request.getEmail())) {
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
}
