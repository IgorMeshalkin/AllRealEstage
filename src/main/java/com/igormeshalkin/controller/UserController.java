package com.igormeshalkin.controller;

import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.SecurityService;
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

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:see your profile')")
    public String profile(Model model) {
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());
        return "users_profile";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users_registration";
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:update your profile')")
    public String updateUser(Model model) {
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("user", SecurityUtil.getCurrentUser());
        return "users_update";
    }

    @RequestMapping(value = "/update_user_with_credentials", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:update your profile')")
    public String updateUserWithCredentials(Model model) {
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("user", SecurityUtil.getCurrentUser());
        return "users_update_with_credentials";
    }

    @RequestMapping(value = "/save_registered_user", method = RequestMethod.POST)
    public String saveRegisteredUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "users_registration";
        }
        userService.create(user);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
        return "redirect:/";
    }

    @RequestMapping(value = "/save_updated_user", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('users:update your profile')")
    public String saveUpdatedUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if(user.getConfirmPassword() != null) {
            userValidator.validate(user, bindingResult);
            if (bindingResult.hasErrors()) {
                return "users_update_with_credentials";
            }
            userService.update(user);
            securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
        } else {
            userValidator.validateUpdate(user, bindingResult);
            if (bindingResult.hasErrors()) {
                return "users_update";
            }
            userService.update(user);
        }
        return "redirect:/api/users/profile";
    }
}
