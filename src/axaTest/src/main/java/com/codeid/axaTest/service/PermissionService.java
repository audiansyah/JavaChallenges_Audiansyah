package com.codeid.axaTest.service;

import com.codeid.axaTest.model.dto.PermissionDto;

public interface PermissionService {
    PermissionDto create(PermissionDto dto);
    PermissionDto update(Long permissionId, PermissionDto dto);
    void delete(Long permissionId);
}

