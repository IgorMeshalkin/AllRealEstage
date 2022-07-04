package com.igormeshalkin.controller;

import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.UserService;
import com.igormeshalkin.util.SecurityUtil;
import com.igormeshalkin.validator.UserValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/admin/users")
public class AdminController {
    private final UserService userService;
    private final UserValidator userValidator;

    public AdminController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:show all')")
    public String getAll(Model model) {
        List<User> result = userService.findAll();
        model.addAttribute("allUsers", result);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        return "users_all";
    }

    @RequestMapping(value = "/change_role", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:update any profiles')")
    public String changeRole(@RequestParam("userId") long id) {
        User user = userService.findById(id);
        userService.changeRole(user);
        return "redirect:/api/admin/users";
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:update any profiles')")
    public String blockUser(@RequestParam("userId") long id) {
        User user = userService.findById(id);
        userService.blockUser(user);
        return "redirect:/api/admin/users";
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:update any profiles')")
    public String updateUser(@RequestParam("userId") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("user", user);
        model.addAttribute("isCredentials", false);
        model.addAttribute("isAdmin", true);
        return "users_update";
    }

    @RequestMapping(value = "/save_updated_user", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('users:update any profiles')")
    public String saveUpdatedUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validateUpdate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "users_update";
        }
        userService.update(user);
        return "redirect:/api/admin/users";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:delete any profiles')")
    public String delete(@RequestParam("userId") long id) {
        userService.delete(id);
        return "redirect:/api/admin/users";
    }
}
