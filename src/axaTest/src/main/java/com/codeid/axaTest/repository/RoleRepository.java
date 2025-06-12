package com.codeid.axaTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeid.axaTest.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
