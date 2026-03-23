package com.sjlee.web.toy.player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/player")
public class PlayerAdminController {

    /**
     * 관리자 선수 목록 화면
     */
    @GetMapping
    public String playerList() {
        return "player/admin/list";
    }
}
