package com.codeid.axaTest.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeid.axaTest.model.dto.RoleDto;
import com.codeid.axaTest.model.entity.Role;
import com.codeid.axaTest.repository.RoleRepository;
import com.codeid.axaTest.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;

    @Override
    public List<RoleDto> findAll() {
        return roleRepo.findAll().stream()
                .map(r -> new RoleDto(r.getRoleId(), r.getRoleName()))
                .toList();
    }

    @Override
    public RoleDto findById(Long id) {
        Role role = roleRepo.findById(id).orElseThrow();
        return new RoleDto(role.getRoleId(), role.getRoleName());
    }

    @Override
    public RoleDto save(RoleDto dto) {
        Role role = new Role();
        role.setRoleName(dto.roleName());
        role = roleRepo.save(role);
        return new RoleDto(role.getRoleId(), role.getRoleName());
    }

    @Override
    public RoleDto update(Long id, RoleDto dto) {
        Role role = roleRepo.findById(id).orElseThrow();
        role.setRoleName(dto.roleName());
        roleRepo.save(role);
        return dto;
    }

    @Override
    public void delete(Long id) {
        roleRepo.deleteById(id);
    }
}


