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
    public String likeApartmentByMain(@RequestParam("apartmentId") long apartment_id,
                                      HttpServletRequest request) {
        likeService.like(apartment_id);
        return "redirect:" + request.getHeader("referer");
    }

//    @RequestMapping(value = "/apartments_by_details", method = RequestMethod.GET)
//    public String likeApartmentByDetails(@RequestParam("apartmentId") long apartment_id, Model model) {
//        likeService.like(apartment_id);
//        model.addAttribute("apartmentId", apartment_id);
//        return "redirect:/details";
//    }
}
