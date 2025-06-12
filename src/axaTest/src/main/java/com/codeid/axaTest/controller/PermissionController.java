package com.codeid.axaTest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeid.axaTest.model.dto.PermissionDto;
import com.codeid.axaTest.service.BaseCrudService;
import com.codeid.axaTest.service.PermissionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController extends BaseCrudController<PermissionDto, Long> {
    private final PermissionService permissionService;

    @Override
    protected BaseCrudService<PermissionDto, Long> getService(){
        return permissionService;
    }

    @Override
    public ResponseEntity<List<PermissionDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<PermissionDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<PermissionDto> update(Long id, @Valid PermissionDto entity) {
        return super.update(id, entity);
    }

    @Override
    public ResponseEntity<PermissionDto> create(@Valid PermissionDto entity) {
        return super.create(entity);
    }
}

