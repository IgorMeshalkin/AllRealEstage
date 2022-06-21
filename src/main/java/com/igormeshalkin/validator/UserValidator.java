package com.igormeshalkin.validator;

import com.igormeshalkin.dao.UserDAO;
import com.igormeshalkin.entity.User;
import com.igormeshalkin.util.SecurityUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.NoResultException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private final UserDAO userDAO;

    public UserValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "This field is required");
        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "", "Username must be between 3 and 32 characters");
        }

        if (!SecurityUtil.isAuthentication() || !user.getUsername().equals(SecurityUtil.getCurrentUser().getUsername())) {
            if (isNameExists(user.getUsername())) {
                errors.rejectValue("username", "", "Such username already exists");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "", "This field is required");
        if (!phoneNumberVerification(user.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "", "Invalid phone number");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "This field is required");
        if (user.getPassword().length() < 3 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "", "Password must be over 3 characters.");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "", "Password don't match");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "This field is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "This field is required");
    }

    public void validateUpdate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "This field is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "This field is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "", "This field is required");
        if (!phoneNumberVerification(user.getPhoneNumber())) {
            errors.rejectValue("phoneNumber", "", "Invalid phone number");
        }
    }

    private boolean isNameExists(String username) {
        User fromDb = null;

        try {
            fromDb = userDAO.findByUsername(username);
        } catch (NoResultException ignore) {
        }

        return fromDb != null;
    }

    private boolean phoneNumberVerification(String phoneNumber) {
        String numberOnly= phoneNumber.replaceAll("[^0-9]", "");
        Pattern pattern = Pattern.compile("^((\\+7|7|8)+([0-9]){10})$");
        Matcher matcher = pattern.matcher(numberOnly);
        return matcher.matches();
    }
}
