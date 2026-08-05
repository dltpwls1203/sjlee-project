package com.sjlee.web.toy.ground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/grounds")
public class GroundAdminController {

    /**
     * 관리자 구장 목록 화면
     */
    @GetMapping
    public String groundList() {
        return "ground/admin/list";
    }

    /**
     * 관리자 구장 등록 화면
     */
    @GetMapping("/new")
    public String createGroundForm() {
        return "ground/admin/form";
    }
}
