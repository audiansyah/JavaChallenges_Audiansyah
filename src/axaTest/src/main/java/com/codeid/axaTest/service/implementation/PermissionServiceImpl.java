package com.codeid.axaTest.service.implementation;

import org.springframework.stereotype.Service;

import com.codeid.axaTest.model.dto.PermissionDto;
import com.codeid.axaTest.model.entity.Permission;
import com.codeid.axaTest.model.entity.Role;
import com.codeid.axaTest.repository.PermissionRepository;
import com.codeid.axaTest.repository.RoleRepository;
import com.codeid.axaTest.service.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepo;
    private final RoleRepository roleRepo;

    @Override
    public PermissionDto create(PermissionDto dto) {
        Role role = roleRepo.findById(dto.roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        Permission permission = new Permission();
        permission.setPermissionType(dto.permissionType());
        permission.setRole(role);
        permission = permissionRepo.save(permission);
        return new PermissionDto(permission.getPermissionId(), permission.getPermissionType(), role.getRoleId());
    }

    @Override
    public PermissionDto update(Long permissionId, PermissionDto dto) {
        Permission permission = permissionRepo.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        permission.setPermissionType(dto.permissionType());
        if (!permission.getRole().getRoleId().equals(dto.roleId())) {
            Role role = roleRepo.findById(dto.roleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            permission.setRole(role);
        }
        permission = permissionRepo.save(permission);
        return new PermissionDto(permission.getPermissionId(), permission.getPermissionType(), permission.getRole().getRoleId());
    }

    @Override
    public void delete(Long permissionId) {
        permissionRepo.deleteById(permissionId);
    }
}

