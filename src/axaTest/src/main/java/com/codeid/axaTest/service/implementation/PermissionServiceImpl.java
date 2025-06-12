package com.codeid.axaTest.service.implementation;

import java.util.List;

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
    public List<PermissionDto> findAll() {
        return permissionRepo.findAll().stream()
                .map(p -> new PermissionDto(p.getPermissionId(), p.getPermissionType(), p.getRole().getRoleId()))
                .toList();
    }

    @Override
    public PermissionDto findById(Long id) {
        Permission p = permissionRepo.findById(id).orElseThrow();
        return new PermissionDto(p.getPermissionId(), p.getPermissionType(), p.getRole().getRoleId());
    }

    @Override
    public PermissionDto save(PermissionDto dto) {
        Role role = roleRepo.findById(dto.roleId()).orElseThrow();
        Permission p = new Permission();
        p.setPermissionType(dto.permissionType());
        p.setRole(role);
        p = permissionRepo.save(p);
        return new PermissionDto(p.getPermissionId(), p.getPermissionType(), p.getRole().getRoleId());
    }

    @Override
    public PermissionDto update(Long id, PermissionDto dto) {
        Permission p = permissionRepo.findById(id).orElseThrow();
        Role role = roleRepo.findById(dto.roleId()).orElseThrow();
        p.setPermissionType(dto.permissionType());
        p.setRole(role);
        permissionRepo.save(p);
        return dto;
    }

    @Override
    public void delete(Long id) {
        permissionRepo.deleteById(id);
    }
}


