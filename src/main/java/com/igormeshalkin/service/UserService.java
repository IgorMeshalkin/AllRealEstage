package com.igormeshalkin.service;

import com.igormeshalkin.dao.UserDAO;
import com.igormeshalkin.entity.Role;
import com.igormeshalkin.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public void create(User user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setCreated(currentDateTime);
        user.setUpdated(currentDateTime);

        user.setPhoneNumber(phoneNumberFormat(user.getPhoneNumber()));
        user.setRole(Role.USER);
        user.setActive(true);

        user.setPassword(passwordEncoderFromUserService().encode(user.getPassword()));

        userDAO.saveOrUpdate(user);
    }

    public void update(User user) {
        User fromDb = userDAO.findById(user.getId());
        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setCreated(fromDb.getCreated());
        user.setUpdated(currentDateTime);

        if(!user.getPhoneNumber().equals(fromDb.getPhoneNumber())) {
            user.setPhoneNumber(phoneNumberFormat(user.getPhoneNumber()));
        }

        user.setRole(fromDb.getRole());
        user.setActive(fromDb.isActive());

        if(user.getConfirmPassword() != null) {
            user.setPassword(passwordEncoderFromUserService().encode(user.getPassword()));
        }

        userDAO.saveOrUpdate(user);
    }

    public void changeRole(User user) {
        User fromDb = userDAO.findById(user.getId());

        LocalDateTime currentDateTime = LocalDateTime.now();
        fromDb.setUpdated(currentDateTime);
        if(fromDb.getRole().equals(Role.ADMIN)) {
            fromDb.setRole(Role.USER);
        } else {
            fromDb.setRole(Role.ADMIN);
        }
        userDAO.saveOrUpdate(fromDb);
    }

    public void blockUser(User user) {
        User fromDb = userDAO.findById(user.getId());

        LocalDateTime currentDateTime = LocalDateTime.now();
        fromDb.setUpdated(currentDateTime);
        if(fromDb.isActive()) {
            fromDb.setActive(false);
        } else {
            fromDb.setActive(true);
        }
        userDAO.saveOrUpdate(fromDb);
    }

    public void delete(Long id) {
            userDAO.delete(id);
    }

    @Bean
    protected PasswordEncoder passwordEncoderFromUserService() {
        return new BCryptPasswordEncoder();
    }

    protected String phoneNumberFormat(String phoneNumber) {
        String numberOnly= phoneNumber.replaceAll("[^0-9]", "");

        StringBuilder builder = new StringBuilder();
        builder.append("+7 (" + numberOnly.substring(1, 4)
                + ") " + numberOnly.substring(4, 7)
                + "-" + numberOnly.substring(7, 9)
                + "-" + numberOnly.substring(9));

        return builder.toString();
    }
}
