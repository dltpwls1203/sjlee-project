package com.sjlee.web.toy.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
