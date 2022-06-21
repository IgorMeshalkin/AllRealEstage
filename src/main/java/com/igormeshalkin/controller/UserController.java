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

    @RequestMapping("/registration")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping("/update_user")
    public String updateUser(Model model) {
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("user", SecurityUtil.getCurrentUser());
        return "update-user";
    }

    @RequestMapping("/update_user_with_credentials")
    public String updateUserWithCredentials(Model model) {
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("user", SecurityUtil.getCurrentUser());
        return "update-user-with-credentials";
    }

    @RequestMapping(value = "/save_registered_user")
    public String saveRegisteredUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.create(user);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
        return "redirect:/";
    }

    @RequestMapping(value = "/save_updated_user")
    public String saveUpdatedUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if(user.getConfirmPassword() != null) {
            userValidator.validate(user, bindingResult);
            if (bindingResult.hasErrors()) {
                return "update-user-with-credentials";
            }
            userService.update(user);
            securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
        } else {
            userValidator.validateUpdate(user, bindingResult);
            if (bindingResult.hasErrors()) {
                return "update-user";
            }
            userService.update(user);
        }
        return "redirect:/api/users/profile";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());
        return "profile";
    }

//
//    @RequestMapping("/delete")
//    public String delete(@RequestParam("userId") long id) {
//        userService.delete(id);
//        return "redirect:/";
//    }

}
