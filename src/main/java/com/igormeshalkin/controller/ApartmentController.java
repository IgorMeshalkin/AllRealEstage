package com.igormeshalkin.controller;

import com.igormeshalkin.entity.Apartment;
import com.igormeshalkin.service.ApartmentService;
import com.igormeshalkin.util.SecurityUtil;
import com.igormeshalkin.validator.ApartmentValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class ApartmentController {
    private final ApartmentService apartmentService;
    private final ApartmentValidator apartmentValidator;

    public ApartmentController(ApartmentService apartmentService, ApartmentValidator apartmentValidator) {
        this.apartmentService = apartmentService;
        this.apartmentValidator = apartmentValidator;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Model model,
                          @RequestParam(value = "address", required = false) String address,
                          @RequestParam(value = "roomsMin", required = false) Integer roomsMin,
                          @RequestParam(value = "roomsMax", required = false) Integer roomsMax,
                          @RequestParam(value = "areaMin", required = false) Double areaMin,
                          @RequestParam(value = "areaMax", required = false) Double areaMax,
                          @RequestParam(value = "floorMin", required = false) Integer floorMin,
                          @RequestParam(value = "floorMax", required = false) Integer floorMax,
                          @RequestParam(value = "totalFloorsMin", required = false) Integer totalFloorsMin,
                          @RequestParam(value = "totalFloorsMax", required = false) Integer totalFloorsMax,
                          @RequestParam(value = "priceMin", required = false) Integer priceMin,
                          @RequestParam(value = "priceMax", required = false) Integer priceMax,
                          @RequestParam(value = "balconyTrue", required = false) boolean balcony,
                          @RequestParam(value = "sort", required = false, defaultValue = "Created") String sort) {

        List<Apartment> result = apartmentService.findAll().stream()
                .filter(apartment -> address == null || apartment.getAddressFormat().contains(address))
                .filter(apartment -> roomsMin == null || apartment.getNumberOfRooms() >= roomsMin)
                .filter(apartment -> roomsMax == null || apartment.getNumberOfRooms() <= roomsMax)
                .filter(apartment -> areaMin == null || apartment.getArea() >= areaMin)
                .filter(apartment -> areaMax == null || apartment.getArea() <= areaMax)
                .filter(apartment -> floorMin == null || apartment.getFloor() >= floorMin)
                .filter(apartment -> floorMax == null || apartment.getFloor() <= floorMax)
                .filter(apartment -> totalFloorsMin == null || apartment.getTotalFloors() >= totalFloorsMin)
                .filter(apartment -> totalFloorsMax == null || apartment.getTotalFloors() <= totalFloorsMax)
                .filter(apartment -> priceMin == null || apartment.getPrice() >= priceMin)
                .filter(apartment -> priceMax == null || apartment.getPrice() <= priceMax)
                .filter(apartment -> !balcony || apartment.getBalconyAvailability())
                .sorted(((apartment1, apartment2) -> {
                    if(sort.equals("New ones first")) {
                        return apartment2.getCreated().compareTo(apartment1.getCreated());
                    } else if (sort.equals("Rooms")) {
                        return apartment1.getNumberOfRooms().compareTo(apartment2.getNumberOfRooms());
                    } else if (sort.equals("Area")) {
                        return apartment1.getArea().compareTo(apartment2.getArea());
                    } else if (sort.equals("Price")) {
                        return apartment1.getPrice().compareTo(apartment2.getPrice());
                    }
                    return apartment1.getCreated().compareTo(apartment2.getCreated());
                }))
                .collect(Collectors.toList());

        model.addAttribute("allApartments", result);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());
        model.addAttribute("isFavorites", "No");
        model.addAttribute("action", "/");
        return "main";
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String showFavorites(Model model,
                                @RequestParam(value = "address", required = false) String address,
                                @RequestParam(value = "roomsMin", required = false) Integer roomsMin,
                                @RequestParam(value = "roomsMax", required = false) Integer roomsMax,
                                @RequestParam(value = "areaMin", required = false) Double areaMin,
                                @RequestParam(value = "areaMax", required = false) Double areaMax,
                                @RequestParam(value = "floorMin", required = false) Integer floorMin,
                                @RequestParam(value = "floorMax", required = false) Integer floorMax,
                                @RequestParam(value = "totalFloorsMin", required = false) Integer totalFloorsMin,
                                @RequestParam(value = "totalFloorsMax", required = false) Integer totalFloorsMax,
                                @RequestParam(value = "priceMin", required = false) Integer priceMin,
                                @RequestParam(value = "priceMax", required = false) Integer priceMax,
                                @RequestParam(value = "balconyTrue", required = false) boolean balcony,
                                @RequestParam(value = "sort", required = false, defaultValue = "Created") String sort) {

        List<Apartment> result = apartmentService.findAll()
                .stream()
                .filter(Apartment::isLikedByCurrentUser)
                .filter(apartment -> address == null || apartment.getAddressFormat().contains(address))
                .filter(apartment -> roomsMin == null || apartment.getNumberOfRooms() >= roomsMin)
                .filter(apartment -> roomsMax == null || apartment.getNumberOfRooms() <= roomsMax)
                .filter(apartment -> areaMin == null || apartment.getArea() >= areaMin)
                .filter(apartment -> areaMax == null || apartment.getArea() <= areaMax)
                .filter(apartment -> floorMin == null || apartment.getFloor() >= floorMin)
                .filter(apartment -> floorMax == null || apartment.getFloor() <= floorMax)
                .filter(apartment -> totalFloorsMin == null || apartment.getTotalFloors() >= totalFloorsMin)
                .filter(apartment -> totalFloorsMax == null || apartment.getTotalFloors() <= totalFloorsMax)
                .filter(apartment -> priceMin == null || apartment.getPrice() >= priceMin)
                .filter(apartment -> priceMax == null || apartment.getPrice() <= priceMax)
                .filter(apartment -> !balcony || apartment.getBalconyAvailability())
                .sorted(((apartment1, apartment2) -> {
                    if(sort.equals("New ones first")) {
                        return apartment2.getCreated().compareTo(apartment1.getCreated());
                    } else if (sort.equals("Rooms")) {
                        return apartment1.getNumberOfRooms().compareTo(apartment2.getNumberOfRooms());
                    } else if (sort.equals("Area")) {
                        return apartment1.getArea().compareTo(apartment2.getArea());
                    } else if (sort.equals("Price")) {
                        return apartment1.getPrice().compareTo(apartment2.getPrice());
                    }
                    return apartment1.getCreated().compareTo(apartment2.getCreated());
                }))
                .collect(Collectors.toList());

        model.addAttribute("allApartments", result);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());
        model.addAttribute("isFavorites", "Yes");
        return "main";
    }

    @RequestMapping(value = "/my_apartments", method = RequestMethod.GET)
    public String showMyApartments(Model model,
                                   @RequestParam(value = "address", required = false) String address,
                                   @RequestParam(value = "roomsMin", required = false) Integer roomsMin,
                                   @RequestParam(value = "roomsMax", required = false) Integer roomsMax,
                                   @RequestParam(value = "areaMin", required = false) Double areaMin,
                                   @RequestParam(value = "areaMax", required = false) Double areaMax,
                                   @RequestParam(value = "floorMin", required = false) Integer floorMin,
                                   @RequestParam(value = "floorMax", required = false) Integer floorMax,
                                   @RequestParam(value = "totalFloorsMin", required = false) Integer totalFloorsMin,
                                   @RequestParam(value = "totalFloorsMax", required = false) Integer totalFloorsMax,
                                   @RequestParam(value = "priceMin", required = false) Integer priceMin,
                                   @RequestParam(value = "priceMax", required = false) Integer priceMax,
                                   @RequestParam(value = "balconyTrue", required = false) boolean balcony,
                                   @RequestParam(value = "sort", required = false, defaultValue = "Created") String sort) {
        List<Apartment> result = apartmentService.findAll()
                .stream()
                .filter(Apartment::isOwnedByCurrentUser)
                .filter(apartment -> address == null || apartment.getAddressFormat().contains(address))
                .filter(apartment -> roomsMin == null || apartment.getNumberOfRooms() >= roomsMin)
                .filter(apartment -> roomsMax == null || apartment.getNumberOfRooms() <= roomsMax)
                .filter(apartment -> areaMin == null || apartment.getArea() >= areaMin)
                .filter(apartment -> areaMax == null || apartment.getArea() <= areaMax)
                .filter(apartment -> floorMin == null || apartment.getFloor() >= floorMin)
                .filter(apartment -> floorMax == null || apartment.getFloor() <= floorMax)
                .filter(apartment -> totalFloorsMin == null || apartment.getTotalFloors() >= totalFloorsMin)
                .filter(apartment -> totalFloorsMax == null || apartment.getTotalFloors() <= totalFloorsMax)
                .filter(apartment -> priceMin == null || apartment.getPrice() >= priceMin)
                .filter(apartment -> priceMax == null || apartment.getPrice() <= priceMax)
                .filter(apartment -> !balcony || apartment.getBalconyAvailability())
                .sorted(((apartment1, apartment2) -> {
                    if(sort.equals("New ones first")) {
                        return apartment2.getCreated().compareTo(apartment1.getCreated());
                    } else if (sort.equals("Rooms")) {
                        return apartment1.getNumberOfRooms().compareTo(apartment2.getNumberOfRooms());
                    } else if (sort.equals("Area")) {
                        return apartment1.getArea().compareTo(apartment2.getArea());
                    } else if (sort.equals("Price")) {
                        return apartment1.getPrice().compareTo(apartment2.getPrice());
                    }
                    return apartment1.getCreated().compareTo(apartment2.getCreated());
                }))
                .collect(Collectors.toList());
        model.addAttribute("allApartments", result);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());
        return "apartments_my";
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(@RequestParam("apartmentId") long apartment_id, Model model, HttpServletRequest request) {
        Apartment apartment = apartmentService.findById(apartment_id);
        model.addAttribute("apartment", apartment);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("currentUser", SecurityUtil.getCurrentUser());

        request.getSession().setAttribute("lastPage", request.getHeader("referer"));

        if (apartment.isOwnedByCurrentUser()) {
            return "apartments_details_for_owner";
        }
        return "apartments_details";
    }

    @RequestMapping(value = "/create_apartment", method = RequestMethod.GET)
    public String createApartment(Model model, HttpServletRequest request) {
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("action", "/save_created_apartment");

        request.getSession().setAttribute("lastPage", request.getHeader("referer"));

        return "apartments_create_or_update";
    }

    @RequestMapping(value = "/save_created_apartment", method = RequestMethod.POST)
    public String saveCreatedApartment(@ModelAttribute("apartment") Apartment apartment, BindingResult bindingResult, HttpServletRequest request) {
        apartmentValidator.validate(apartment, bindingResult);
        if (bindingResult.hasErrors()) {
            return "apartments_create_or_update";
        }

        apartmentService.create(apartment);

        HttpSession session = request.getSession();
        String lastPage = (String) session.getAttribute("lastPage");
        return "redirect:" + lastPage;
    }

    @RequestMapping(value = "/update_apartment", method = RequestMethod.GET)
    public String updateApartment(@RequestParam("apartmentId") long id, Model model, HttpServletRequest request) {
        Apartment apartment = apartmentService.findById(id);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("apartment", apartment);
        model.addAttribute("action", "/save_updated_apartment");

        request.getSession().setAttribute("lastPage", request.getHeader("referer"));

        return "apartments_create_or_update";
    }

    @RequestMapping(value = "/save_updated_apartment", method = RequestMethod.POST)
    public String saveUpdatedUser(@ModelAttribute("apartment") Apartment apartment, BindingResult bindingResult, HttpServletRequest request) {
        apartmentValidator.validate(apartment, bindingResult);
        if (bindingResult.hasErrors()) {
            return "apartments_create_or_update";
        }

        apartmentService.update(apartment);

        HttpSession session = request.getSession();
        String lastPage = (String) session.getAttribute("lastPage");
        return "redirect:" + lastPage;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("apartmentId") long id, HttpServletRequest request) {
        apartmentService.delete(id);

        String lastPage = request.getHeader("referer");
        if (lastPage.contains("/details")) {
            HttpSession session = request.getSession();
            lastPage = (String) session.getAttribute("lastPage");
        }
        return "redirect:" + lastPage;
    }
}
