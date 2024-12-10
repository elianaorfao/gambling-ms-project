package com.gambling.user_service.repository;

import com.gambling.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long UserId);


}
