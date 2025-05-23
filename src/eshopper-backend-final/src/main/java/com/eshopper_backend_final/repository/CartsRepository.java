package com.eshopper_backend_final.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopper_backend_final.model.entity.Carts;

@Repository
public interface CartsRepository extends JpaRepository<Carts, Long> {
    Optional<Carts> findByUsersUserId(Long userId);
}

