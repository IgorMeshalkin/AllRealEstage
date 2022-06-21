package com.igormeshalkin.controller;

import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.SecurityService;
import com.igormeshalkin.service.UserService;
import com.igormeshalkin.util.SecurityUtil;
import com.igormeshalkin.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/admin/users")
public class AdminController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    public AdminController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @RequestMapping
    public String getAll(Model model) {
        List<User> result = userService.findAll();
        model.addAttribute("allUsers", result);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
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
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("user", user);
        return "update-user-for-admin";
    }

    @RequestMapping(value = "/save_updated_user")
    public String saveUpdatedUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validateUpdate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "update-user-for-admin";
        }
        userService.update(user);
        return "redirect:/api/admin/users";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("userId") long id) {
        userService.delete(id);
        return "redirect:/api/admin/users";
    }
}
