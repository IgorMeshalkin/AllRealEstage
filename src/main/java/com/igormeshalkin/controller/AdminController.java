package com.igormeshalkin.controller;

import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.SecurityService;
import com.igormeshalkin.service.UserService;
import com.igormeshalkin.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/admin/users")
public class AdminController {
    private final UserService userService;
    private final SecurityService securityService;

    public AdminController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping
    public String getAll(Model model) {
        List<User> result = userService.findAll();
        model.addAttribute("allUsers", result);
        model.addAttribute("currentUser", SecurityUtil.getCurrentUserFirstNameAndLastName());
        return "all-users";
    }

    @RequestMapping("/change_role")
    public String changeRole(@RequestParam("userId") long id) {
        User user = userService.findById(id);
        userService.changeRole(user);
        return "redirect:/api/admin/users";
    }

    @RequestMapping("/block")
    public String blockUser(@RequestParam("userId") long id) {
        User user = userService.findById(id);
        userService.blockUser(user);
        return "redirect:/api/admin/users";
    }

    @RequestMapping("/update_user")
    public String updateUser(@RequestParam("userId") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("userId") long id) {
        userService.delete(id);
        return "redirect:/api/admin/users";
    }
}
