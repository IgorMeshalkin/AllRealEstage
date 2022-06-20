package com.igormeshalkin.service;

import com.igormeshalkin.dao.UserDAO;
import com.igormeshalkin.entity.Role;
import com.igormeshalkin.entity.User;
import com.igormeshalkin.util.SecurityUtil;
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

//    @Transactional
    public List<User> findAll(){
        return userDAO.findAll();
    }

//    @Transactional
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

//    @Transactional
    public void create(User user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setCreated(currentDateTime);
        user.setUpdated(currentDateTime);
        user.setRole(Role.USER);
        user.setActive(true);

        user.setPassword(passwordEncoderFromUserService().encode(user.getPassword()));

        userDAO.saveOrUpdate(user);
    }

//    @Transactional
    public void update(User user) {
        User fromDb = userDAO.findById(user.getId());
        LocalDateTime currentDateTime = LocalDateTime.now();
        user.setCreated(fromDb.getCreated());
        user.setUpdated(currentDateTime);

        user.setRole(fromDb.getRole());
        user.setActive(fromDb.isActive());

        userDAO.saveOrUpdate(user);
    }

//    @Transactional
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

//    @Transactional
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

//    @Transactional
    public void delete(Long id) {
        userDAO.delete(id);
    }

    @Bean
    protected PasswordEncoder passwordEncoderFromUserService() {
        return new BCryptPasswordEncoder();
    }
}
