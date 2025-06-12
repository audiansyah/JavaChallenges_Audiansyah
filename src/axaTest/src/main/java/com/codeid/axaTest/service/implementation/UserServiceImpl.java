package com.codeid.axaTest.service.implementation;

import org.springframework.stereotype.Service;

import com.codeid.axaTest.model.dto.UserDto;
import com.codeid.axaTest.model.entity.Role;
import com.codeid.axaTest.model.entity.User;
import com.codeid.axaTest.repository.RoleRepository;
import com.codeid.axaTest.repository.UserRepository;
import com.codeid.axaTest.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Override
    public UserDto create(UserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        if (dto.roleId() != null) {
            Role role = roleRepo.findById(dto.roleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
        }
        user = userRepo.save(user);
        return new UserDto(user.getUserId(), user.getUsername(), user.getPassword(),
                user.getRole() != null ? user.getRole().getRoleId() : null);
    }

    @Override
    public UserDto update(Long userId, UserDto dto) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        if (dto.roleId() != null) {
            Role role = roleRepo.findById(dto.roleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
        } else {
            user.setRole(null);
        }
        user = userRepo.save(user);
        return new UserDto(user.getUserId(), user.getUsername(), user.getPassword(),
                user.getRole() != null ? user.getRole().getRoleId() : null);
    }

    @Override
    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public UserDto assignRole(Long userId, Long roleId) {
        User user = userRepo.findById(userId).orElseThrow();
        Role role = roleRepo.findById(roleId).orElseThrow();
        user.setRole(role);
        userRepo.save(user);
        return new UserDto(user.getUserId(), user.getUsername(), user.getPassword(), role.getRoleId());
    }
}

