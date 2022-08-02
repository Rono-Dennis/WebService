package com.example.authservice.repository;


import com.example.authservice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
     User findByUserName(String username);
}
