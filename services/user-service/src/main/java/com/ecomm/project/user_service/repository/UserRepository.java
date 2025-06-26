package com.ecomm.project.user_service.repository;


import com.ecomm.project.user_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods if needed
}
