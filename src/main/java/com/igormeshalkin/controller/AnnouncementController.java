package com.igormeshalkin.controller;

import com.igormeshalkin.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AnnouncementController {

    @RequestMapping
    public String main(Model model) {
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        return "main";
    }
}
