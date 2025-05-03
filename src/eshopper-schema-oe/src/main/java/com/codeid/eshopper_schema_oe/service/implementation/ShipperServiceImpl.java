package com.codeid.eshopper_schema_oe.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeid.eshopper_schema_oe.entities.Shipper;
import com.codeid.eshopper_schema_oe.repository.ShipperRepository;
import com.codeid.eshopper_schema_oe.service.ShipperService;

@Service
public class ShipperServiceImpl implements ShipperService {
     private final ShipperRepository repository;

     //depency injection
     public ShipperServiceImpl(ShipperRepository repository) {
         // loose-couple
         this.repository = repository;
 
         // contoh tight-couple
         //this.repository = new RegionRepository(); 
             
         
     }
 
     @Override
     public List<Shipper> findAllShipper() {
         return repository.findAll();
     }

     @Override
     public Shipper addShipper(Shipper shipper) {
        return repository.save(shipper);
    }

     @Override
     public Optional<Shipper> findShipperById(Long shipperId) {
        return repository.findById(shipperId);
     }

     @Override
     public void deleteShipperById(Long shipperId) {
        repository.deleteById(shipperId);
     }
}
