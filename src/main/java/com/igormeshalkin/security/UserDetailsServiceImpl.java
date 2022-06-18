package com.igormeshalkin.security;

import com.igormeshalkin.dao.UserDAO;
import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
        return SecurityUser.fromUser(user);
    }
}
