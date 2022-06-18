package com.igormeshalkin.util;

import com.igormeshalkin.dao.UserDAO;
import com.igormeshalkin.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    private static UserDAO userDAO;

    public SecurityUtil(UserDAO userDAO) {
        SecurityUtil.userDAO = userDAO;
    }

    public static User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDAO.findByUsername(auth.getName());
    }

    public static String getCurrentUserFirstNameAndLastName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser =  userDAO.findByUsername(auth.getName());
        String result = currentUser.getFirstName() + " " + currentUser.getLastName();
        return result;
    }
}
