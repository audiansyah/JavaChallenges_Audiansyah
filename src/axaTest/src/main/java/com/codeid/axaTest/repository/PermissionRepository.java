package com.codeid.axaTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeid.axaTest.model.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {}
