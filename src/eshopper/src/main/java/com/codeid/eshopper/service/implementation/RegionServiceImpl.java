package com.codeid.eshopper.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeid.eshopper.entities.Region;
import com.codeid.eshopper.repository.RegionRepository;
import com.codeid.eshopper.service.RegionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RegionServiceImpl implements RegionService{
    //kita gunakan untuk inject ke constructor
    private final RegionRepository repository;

    //depency injection
    public RegionServiceImpl(RegionRepository repository) {
        // loose-couple
        this.repository = repository;

        // contoh tight-couple
        //this.repository = new RegionRepository(); 
            
        
    }

    @Override
    public List<Region> findAllCategory() {
        return repository.findAll();
    }

    @Override
    public Region addRegion(Region region) {
        return repository.save(region);
    }

    @Override
    public Region findById(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Region tidak ditemukan dengan id: " + id));
    }

    @Override
    public Region updateRegion(Long id, Region region) {
        Region existing = findById(id);
        existing.setRegionName(region.getRegionName());
        return repository.save(existing);
    }

    @Override
    public void deleteRegion(Long id) {
        repository.deleteById(id);
    }

    
}