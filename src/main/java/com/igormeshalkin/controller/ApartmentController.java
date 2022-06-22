package com.igormeshalkin.controller;

import com.igormeshalkin.entity.Apartment;
import com.igormeshalkin.service.ApartmentService;
import com.igormeshalkin.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ApartmentController {
    ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        List<Apartment> result = apartmentService.findAll();
        model.addAttribute("allApartments", result);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());
        return "main";
    }

    @RequestMapping(value = "/create_apartment", method = RequestMethod.GET)
    public String createApartment(Model model) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        return "apartments_create";
    }

    @RequestMapping(value = "/save_created_apartment", method = RequestMethod.POST)
    public String saveCreatedApartment(@ModelAttribute("apartment") Apartment apartment) {
        apartmentService.create(apartment);
        return "redirect:/";
    }
}
