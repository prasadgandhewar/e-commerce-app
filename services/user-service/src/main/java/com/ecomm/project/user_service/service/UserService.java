package com.ecomm.project.user_service.service;

import com.ecomm.project.user_service.models.User;
import com.ecomm.project.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public String getUsername(int id) {
        System.out.println("Fetching from DB...");
        return getRandomString();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }


    @CacheEvict(value = "users", key = "#id")
    public void evictUserCache(Long id) {
        System.out.println("Evicting cache for user " + id);
    }
}
