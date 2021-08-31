package com.example.project05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project05.model.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
