package com.sjlee.web.toy.global.web.admin;

import com.sjlee.web.toy.dashboard.admin.controller.AdminDashboardController;
import com.sjlee.web.toy.ground.controller.GroundAdminController;
import com.sjlee.web.toy.match.controller.MatchAdminController;
import com.sjlee.web.toy.member.domain.AdminMenu;
import com.sjlee.web.toy.member.domain.Member;
import com.sjlee.web.toy.opponentTeam.controller.OpponentTeamController;
import com.sjlee.web.toy.player.controller.PlayerAdminController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice(assignableTypes = {
        AdminDashboardController.class,
        MatchAdminController.class,
        PlayerAdminController.class,
        GroundAdminController.class,
        OpponentTeamController.class
        // 나중에 AdminMemberController.class 등 추가
})
public class AdminMenuAdvice {
    public static final String LOGIN_MEMBER = "LOGIN_MEMBER";

    @ModelAttribute
    public void addAdminMenus(HttpServletRequest request, HttpSession session, Model model) {

        model.addAttribute("currentUri", request.getRequestURI());

        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if (member == null) return;

        List<AdminMenu> menus = Arrays.stream(AdminMenu.values())
                .filter(menu ->
                        member.getRole().hasPermission(menu.getPermission())
                )
                .toList();

        model.addAttribute("menus", menus);
    }
}
