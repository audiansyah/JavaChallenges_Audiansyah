package com.eshopper_backend_final.service;


import java.util.List;

import org.springframework.data.domain.Pageable;


import com.eshopper_backend_final.model.dto.CartsDto;

public interface CartsService extends BaseCrudService<CartsDto, Long>{
    List<CartsDto> findAll(Pageable pageable);
}
