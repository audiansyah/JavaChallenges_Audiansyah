package com.eshopper_backend_final.controller;



import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopper_backend_final.model.dto.CartsDto;
import com.eshopper_backend_final.model.enumeration.EnumStatus;
import com.eshopper_backend_final.model.response.ApiResponse;
import com.eshopper_backend_final.service.BaseCrudService;
import com.eshopper_backend_final.service.CartsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cart")
@Slf4j
@RequiredArgsConstructor
public class CartsController extends BaseCrudController<CartsDto, Long>{
    
    private final CartsService cartsService;

    @Override
    protected BaseCrudService<CartsDto, Long> getService() {
        return cartsService;
    }

    @Override
    public ResponseEntity<List<CartsDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<CartsDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<CartsDto> create(@Valid CartsDto entity) {
        return super.create(entity);
    }

    //set pagination
    @GetMapping("/paging")
    public ResponseEntity<?> getAllCarts(Pageable pageable){
        var cartsDtos = this.cartsService.findAll(pageable);
        var response = new ApiResponse<>(EnumStatus.Success, "getAllCartsPaging()", cartsDtos);
        return ResponseEntity.ok(response);
    }

}
