package com.sjlee.web.toy.member.service;

import com.sjlee.web.toy.member.domain.Member;
import com.sjlee.web.toy.member.dto.LoginRequest;
import com.sjlee.web.toy.member.dto.MemberCreateRequest;
import com.sjlee.web.toy.member.dto.MemberResponse;

public interface MemberService {

    MemberResponse registerMember(MemberCreateRequest request);
    Member login(LoginRequest request);
}
