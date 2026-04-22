package com.sjlee.web.toy.global.interceptor;

import com.sjlee.web.toy.member.domain.Member;
import com.sjlee.web.toy.member.domain.Permission;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    public static final String LOGIN_MEMBER = "LOGIN_MEMBER";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        HttpSession session = request.getSession(false);

        // 로그인 여부 확인
        if (session == null) {
            response.sendRedirect("/members/login");
            return false;
        }

        Member member = (Member) session.getAttribute(LOGIN_MEMBER);

        if (member == null) {
            response.sendRedirect("/members/login");
            return false;
        }

        // 관리자 권한 확인 (Dashboard 기준)
        if (!member.getRole().hasPermission(Permission.ADMIN_DASHBOARD)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        // 통과
        return true;
    }
}
