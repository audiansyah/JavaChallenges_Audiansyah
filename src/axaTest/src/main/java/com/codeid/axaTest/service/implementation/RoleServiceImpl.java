package com.codeid.axaTest.service.implementation;

import org.springframework.stereotype.Service;

import com.codeid.axaTest.model.dto.RoleDto;
import com.codeid.axaTest.model.entity.Permission;
import com.codeid.axaTest.model.entity.Role;
import com.codeid.axaTest.model.enumeration.EnumStatus;
import com.codeid.axaTest.repository.RoleRepository;
import com.codeid.axaTest.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    @Override
    public RoleDto create(RoleDto dto) {
        Role role = new Role();
        role.setRoleName(dto.roleName());
        role = roleRepo.save(role);
        return new RoleDto(role.getRoleId(), role.getRoleName());
    }

    @Override
    public RoleDto update(Long roleId, RoleDto dto) {
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setRoleName(dto.roleName());
        role = roleRepo.save(role);
        return new RoleDto(role.getRoleId(), role.getRoleName());
    }

    @Override
    public void delete(Long roleId) {
        roleRepo.deleteById(roleId);
    }

    @Override
    public RoleDto applyPermission(Long roleId, EnumStatus type) {
        Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        Permission permission = new Permission();
        permission.setPermissionType(type);
        permission.setRole(role);
        role.getPermissions().add(permission);
        roleRepo.save(role);
        return new RoleDto(role.getRoleId(), role.getRoleName());
    }
}

