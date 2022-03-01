package com.example.serviceroom.config;

import com.example.serviceroom.hotel.user.bo.UserBO;
import com.example.serviceroom.hotel.user.reporisoty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service("userDetailsService")
public class MySQLUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository  userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBO userBO = userRepository.findUserBOByUsername(username);
        if (Objects.nonNull(userBO)){
            User userDetail = new org.springframework.security.core.userdetails.User(userBO.getUsername(),
                    userBO.getPassword(),
                    new ArrayList<>());
            return userDetail;
        }
        throw new UsernameNotFoundException(username +"not found");
    }
}
