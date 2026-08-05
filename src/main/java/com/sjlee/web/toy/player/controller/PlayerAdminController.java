package com.sjlee.web.toy.player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/players")
public class PlayerAdminController {

    /**
     * 관리자 선수 목록 화면
     */
    @GetMapping
    public String playerList() {
        return "player/admin/list";
    }

    /**
     * 관리자 선수 등록 화면
     */
    @GetMapping("/new")
    public String createPlayerForm() {
        return "player/admin/form";
    }


    /**
     * 관리자 선수 상세 화면
     */
    @GetMapping("/detail/{playerId}")
    public String playerDetail(@PathVariable Long playerId, Model model) {
        model.addAttribute("playerId", playerId);
        return "player/admin/detail";
    }
}
