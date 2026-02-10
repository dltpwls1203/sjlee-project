package com.sjlee.web.toy.match.controller;

import com.sjlee.web.toy.match.dto.MatchDetail;
import com.sjlee.web.toy.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/matches")
public class MatchAdminController {

    /**
     * 관리자 경기 목록 화면
     */
    @GetMapping
    public String matchList() {
        return "match/admin/list";
    }

    /**
     * 관리자 경기 등록 화면
     */
    @GetMapping("/new")
    public String createMatchForm() {
        return "match/admin/form";
    }

    /**
     * 관리자 경기 상세 화면
     */
    @GetMapping("/detail/{matchId}")
    public String matchDetail(@PathVariable Long matchId, Model model) {
        model.addAttribute("matchId", matchId);
        return "match/admin/detail";
    }
}
