package com.eshopper_backend_final.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopper_backend_final.model.dto.ShippersDto;
import com.eshopper_backend_final.model.dto.UsersDto;
import com.eshopper_backend_final.service.BaseCrudService;
import com.eshopper_backend_final.service.UsersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UsersController extends BaseCrudController<UsersDto, Long>{
    private final UsersService usersService;

    @Override
    protected BaseCrudService<UsersDto, Long> getService(){
        return usersService;
    }

    @Override
    public ResponseEntity<UsersDto> create(@Valid UsersDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<UsersDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<UsersDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<UsersDto> update(Long id, @Valid UsersDto entity) {
        return super.update(id, entity);
    }
}
