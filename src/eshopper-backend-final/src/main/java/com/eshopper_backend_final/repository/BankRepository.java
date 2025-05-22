package com.eshopper_backend_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopper_backend_final.model.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, String>{
    
}
