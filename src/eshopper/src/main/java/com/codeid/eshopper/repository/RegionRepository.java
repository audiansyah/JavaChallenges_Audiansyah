package com.codeid.eshopper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeid.eshopper.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long>{
    
}