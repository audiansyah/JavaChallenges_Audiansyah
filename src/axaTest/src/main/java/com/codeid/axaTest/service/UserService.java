package com.codeid.axaTest.service;

import com.codeid.axaTest.model.dto.UserDto;

public interface UserService {
    UserDto create(UserDto dto);
    UserDto update(Long userId, UserDto dto);
    void delete(Long userId);
    UserDto assignRole(Long userId, Long roleId);
}

