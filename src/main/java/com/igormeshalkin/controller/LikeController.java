package com.igormeshalkin.controller;

import com.igormeshalkin.service.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/likes")
public class LikeController {
    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String likeApartment(@RequestParam("apartmentId") long apartment_id,
                                      HttpServletRequest request) {
        likeService.like(apartment_id);
        return "redirect:" + request.getHeader("referer");
    }
}
