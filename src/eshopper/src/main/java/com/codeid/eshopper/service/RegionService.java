package com.codeid.eshopper.service;

import java.util.List;

import com.codeid.eshopper.entities.Region;

public interface RegionService {
    List<Region> findAllCategory();
    Region addRegion(Region region);
    Region updateRegion(Long id, Region region);
    void deleteRegion(Long id);
    Region findById(Long id);

}
