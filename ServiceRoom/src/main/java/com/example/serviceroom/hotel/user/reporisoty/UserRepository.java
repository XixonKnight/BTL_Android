package com.example.serviceroom.hotel.user.reporisoty;

import com.example.serviceroom.hotel.user.bo.UserBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBO,Integer> {
    UserBO findUserBOByUsername(String username);
}
