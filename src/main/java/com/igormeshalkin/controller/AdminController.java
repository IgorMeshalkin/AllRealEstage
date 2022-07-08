package com.igormeshalkin.controller;

import com.igormeshalkin.entity.Role;
import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.UserService;
import com.igormeshalkin.util.SecurityUtil;
import com.igormeshalkin.util.UrlAddressUtil;
import com.igormeshalkin.validator.UserValidator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    public String showAll(Model model,
                          @RequestParam(value = "mainFilter", required = false) String mainFilter,
                          @RequestParam(value = "searchLine", required = false) String searchLine,
                          @RequestParam(value = "onlyActive", required = false) boolean onlyActive,
                          @RequestParam(value = "sort", required = false, defaultValue = "Created") String sort,
                          HttpServletRequest request) {
        List<User> result = userService.findAll()
                .stream()
                .filter(user -> {
                    if(mainFilter != null) {
                        if(mainFilter.equals("only_admins")) {
                            return user.getRole().equals(Role.ADMIN);
                        } else if(mainFilter.equals("only_users")) {
                            return user.getRole().equals(Role.USER);
                        }
                    }
                    return user.getId() != null;
                })
                .filter(user -> searchLine == null || user.searchByLine(searchLine))
                .filter(user -> !onlyActive || user.isActive())
                .sorted(((user1, user2) -> {
                        if (sort.equals("Rating")) {
                            return user2.getRating().compareTo(user1.getRating());
                        } else if (sort.equals("First name")) {
                            return user1.getFirstName().compareTo(user2.getFirstName());
                        } else if (sort.equals("Last name")) {
                            return user1.getLastName().compareTo(user2.getLastName());
                        }
                    return user1.getCreated().compareTo(user2.getCreated());
                }))
                .collect(Collectors.toList());

        model.addAttribute("allUsers", result);
        model.addAttribute("currentUserName", SecurityUtil.getCurrentUserFirstNameAndLastName());
        model.addAttribute("mainFilter", mainFilter);
        model.addAttribute("queryIsEmpty", UrlAddressUtil.isEmpty(request.getQueryString()));
        model.addAttribute("addressForReset", UrlAddressUtil.getAddressForResetUsersPage(request.getQueryString()));
        return "users_all";
    }

    @RequestMapping(value = "/change_role", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:update any profiles')")
    public String changeRole(@RequestParam("userId") long id, HttpServletRequest request) {
        User user = userService.findById(id);
        userService.changeRole(user);
        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('users:update any profiles')")
    public String blockUser(@RequestParam("userId") long id, HttpServletRequest request) {
        User user = userService.findById(id);
        userService.blockUser(user);
        return "redirect:" + request.getHeader("referer");
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
