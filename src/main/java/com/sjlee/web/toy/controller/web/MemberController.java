package com.sjlee.web.toy.controller.web;

import com.sjlee.web.toy.member.domain.Member;
import com.sjlee.web.toy.member.domain.MemberRole;
import com.sjlee.web.toy.member.dto.LoginRequest;
import com.sjlee.web.toy.member.exception.LoginFailException;
import com.sjlee.web.toy.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(Model model) {
        return "member/login";
    }

    @GetMapping("/join")
    public String join(Model model) {
        return "member/join";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute LoginRequest request,
            HttpSession session,
            Model model
    ) {
        try {
            Member member = memberService.login(request);

            session.setAttribute("LOGIN_MEMBER", member);

            if (member.getRole() == MemberRole.ADMIN || member.getRole() == MemberRole.SUPER_ADMIN) {
                return "redirect:/admin";
            }
            return "redirect:/members/home";

        } catch (LoginFailException e) {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "member/login";
        }
    }

}
