package com.codeid.axaTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeid.axaTest.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
