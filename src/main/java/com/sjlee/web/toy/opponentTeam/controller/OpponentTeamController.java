package com.sjlee.web.toy.opponentTeam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/opponent-teams")
public class OpponentTeamController {

    /**
     * 관리자 상대팀 목록 화면
     */
    @GetMapping
    public String opponentTeamList() {
        return "opponentTeam/admin/list";
    }
}
