package com.sjlee.web.toy.member.domain;

import java.util.Set;

public enum MemberRole {
    SUPER_ADMIN(Set.of(
            Permission.ADMIN_DASHBOARD,
            Permission.ADMIN_MATCH
    )),

    ADMIN(Set.of(
            Permission.ADMIN_DASHBOARD,
            Permission.ADMIN_MATCH
    )),

    USER(Set.of()); // 관리자 메뉴 없음

    private final Set<Permission> permissions;

    MemberRole(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(Permission permission) {
        return permissions.contains(permission);
    }
}
