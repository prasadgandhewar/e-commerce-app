package com.ecomm.project.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {


    @Cacheable(value = "users", key = "#id")
    public String getUsername(int id) {
        System.out.println("Fetching from DB...");
        return getRandomString();
    }

    public String getRandomString() {
        return UUID.randomUUID().toString();
    }


    @CacheEvict(value = "users", key = "#id")
    public void evictUserCache(Long id) {
        System.out.println("Evicting cache for user " + id);
    }
}
