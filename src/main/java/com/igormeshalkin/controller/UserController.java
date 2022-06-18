package com.igormeshalkin.controller;

import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.SecurityService;
import com.igormeshalkin.service.UserService;
import com.igormeshalkin.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping("/registration")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = "/save_registered_user")
    public String saveRegisteredUser(@ModelAttribute("user") User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "redirect:/api/users/registration";
        }
        userService.create(user);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());

        return "redirect:/";
    }

    @RequestMapping(value = "/save_updated_user")
    public String saveUpdatedUser(@ModelAttribute("user") User user) {
        userService.create(user);
        if (SecurityUtil.getCurrentUser().equals(user)) {
            return "redirect:/";
        } else {
            return "redirect:/api/admin/users";
        }
    }

//    @RequestMapping("/updateUser")
//    public String updateUser(@RequestParam("userId") long id, Model model) {
//        User user = userService.findById(id);
//        model.addAttribute("user", user);
//        return "user-info";
//    }
//
//    @RequestMapping("/delete")
//    public String delete(@RequestParam("userId") long id) {
//        userService.delete(id);
//        return "redirect:/";
//    }

}
