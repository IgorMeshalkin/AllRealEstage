package com.igormeshalkin.service;

import com.igormeshalkin.dao.UserDAO;
import com.igormeshalkin.entity.Role;
import com.igormeshalkin.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Transactional
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Transactional
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

    @Transactional
    public void update(User user) {
        if (user.getUsername().equals("admin")) {
            return;
        }

        User fromDb = userDAO.findById(user.getId());
        LocalDateTime currentDateTime = LocalDateTime.now();
        fromDb.setUpdated(currentDateTime);

        fromDb.setUsername(user.getUsername());
        fromDb.setFirstName(user.getFirstName());
        fromDb.setLastName(user.getLastName());

        if (!user.getPhoneNumber().equals(fromDb.getPhoneNumber())) {
            fromDb.setPhoneNumber(phoneNumberFormat(user.getPhoneNumber()));
        }

        if (user.getConfirmPassword() != null) {
            fromDb.setPassword(passwordEncoderFromUserService().encode(user.getPassword()));
        }

        userDAO.saveOrUpdate(fromDb);
    }

    @Transactional
    public void changeRole(User user) {
        if (user.getUsername().equals("admin")) {
            return;
        }

        User fromDb = userDAO.findById(user.getId());

        LocalDateTime currentDateTime = LocalDateTime.now();
        fromDb.setUpdated(currentDateTime);
        if (fromDb.getRole().equals(Role.ADMIN)) {
            fromDb.setRole(Role.USER);
        } else {
            fromDb.setRole(Role.ADMIN);
        }
        userDAO.saveOrUpdate(fromDb);
    }

    @Transactional
    public void blockUser(User user) {
        if (user.getUsername().equals("admin")) {
            return;
        }

        User fromDb = userDAO.findById(user.getId());

        LocalDateTime currentDateTime = LocalDateTime.now();
        fromDb.setUpdated(currentDateTime);
        if (fromDb.isActive()) {
            fromDb.setActive(false);
        } else {
            fromDb.setActive(true);
        }
        userDAO.saveOrUpdate(fromDb);
    }

    @Transactional
    public void delete(Long id) {
        if (id == 100000) {
            return;
        }
        userDAO.delete(id);
    }

    @Bean
    protected PasswordEncoder passwordEncoderFromUserService() {
        return new BCryptPasswordEncoder();
    }

    protected String phoneNumberFormat(String phoneNumber) {
        String numberOnly = phoneNumber.replaceAll("[^0-9]", "");

        StringBuilder builder = new StringBuilder();
        builder.append("+7 (" + numberOnly.substring(1, 4)
                + ") " + numberOnly.substring(4, 7)
                + "-" + numberOnly.substring(7, 9)
                + "-" + numberOnly.substring(9));

        return builder.toString();
    }
}
