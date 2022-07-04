package com.igormeshalkin.validator;

import com.igormeshalkin.dao.ApartmentDAO;
import com.igormeshalkin.entity.Apartment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ApartmentValidator implements Validator {

    private final ApartmentDAO apartmentDAO;

    public ApartmentValidator(ApartmentDAO apartmentDAO) {
        this.apartmentDAO = apartmentDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Apartment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Apartment apartment = (Apartment) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "", "This field is required");
        if (apartment.getStreet().length() < 3 || apartment.getStreet().length() > 32) {
            errors.rejectValue("street", "", "Street name must be between 3 and 32 characters");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNumber", "", "This field is required");

        if (apartment.getNumberOfRooms() == null) {
            errors.rejectValue("numberOfRooms", "", "This field is required");
        } else if (apartment.getNumberOfRooms() <= 0) {
            errors.rejectValue("numberOfRooms", "", "The number of rooms cannot be less than or equal to zero");
        }

        if (apartment.getArea() == null) {
            errors.rejectValue("area", "", "This field is required");
        } else if (apartment.getArea() <= 0) {
            errors.rejectValue("area", "", "Area cannot be less than or equal to zero");
        }

        if (apartment.getFloor() == null) {
            errors.rejectValue("floor", "", "This field is required");
        } else if (apartment.getFloor() <= 0) {
            errors.rejectValue("floor", "", "Floor cannot be less than or equal to zero");
        }

        if (apartment.getTotalFloors() == null) {
            errors.rejectValue("totalFloors", "", "This field is required");
        } else if (apartment.getTotalFloors() <= 0) {
            errors.rejectValue("totalFloors", "", "The number of floors cannot be less than or equal to zero");
        }

        if (apartment.getPrice() == null) {
            errors.rejectValue("price", "", "This field is required");
        } else if (apartment.getPrice() <= 0) {
            errors.rejectValue("price", "", "The price cannot be less than or equal to zero");
        }
    }
}
