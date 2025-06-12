package com.codeid.axaTest.service;

import com.codeid.axaTest.model.dto.RoleDto;
import com.codeid.axaTest.model.enumeration.EnumStatus;

public interface RoleService {
    RoleDto create(RoleDto dto);
    RoleDto update(Long roleId, RoleDto dto);
    void delete(Long roleId);
    RoleDto applyPermission(Long roleId, EnumStatus type);
}

