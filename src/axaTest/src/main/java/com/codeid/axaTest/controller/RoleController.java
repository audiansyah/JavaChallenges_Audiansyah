package com.codeid.axaTest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeid.axaTest.model.dto.RoleDto;
import com.codeid.axaTest.service.BaseCrudService;
import com.codeid.axaTest.service.RoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController extends BaseCrudController<RoleDto, Long> {
    private final RoleService roleService;

    @Override
    protected BaseCrudService<RoleDto, Long> getService(){
        return roleService;
    }

    @Override
    public ResponseEntity<List<RoleDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<RoleDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<RoleDto> update(Long id, @Valid RoleDto entity) {
        return super.update(id, entity);
    }

    @Override
    public ResponseEntity<RoleDto> create(@Valid RoleDto entity) {
        return super.create(entity);
    }
}

