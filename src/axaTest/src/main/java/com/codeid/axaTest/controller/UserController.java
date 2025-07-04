package com.codeid.axaTest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeid.axaTest.model.dto.UserDto;
import com.codeid.axaTest.service.BaseCrudService;
import com.codeid.axaTest.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController extends BaseCrudController<UserDto, Long> {
    private final UserService userService;

    @Override
    protected BaseCrudService<UserDto, Long> getService(){
        return userService;
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<UserDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<UserDto> update(Long id, @Valid UserDto entity) {
        return super.update(id, entity);
    }

    @Override
    public ResponseEntity<UserDto> create(@Valid UserDto entity) {
        return super.create(entity);
    }
}


