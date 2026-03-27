package com.sjlee.web.toy.member.domain;

import lombok.Getter;

@Getter
public enum AdminMenu {

    DASHBOARD("대시보드", "/admin", Permission.ADMIN_DASHBOARD),
    MATCH("경기 관리", "/admin/matches", Permission.ADMIN_MATCH),
    PLAYER("선수 관리", "/admin/players", Permission.ADMIN_PLAYER);

    private final String title;
    private final String url;
    private final Permission permission;

    AdminMenu(String title, String url, Permission permission) {
        this.title = title;
        this.url = url;
        this.permission = permission;
    }
}
