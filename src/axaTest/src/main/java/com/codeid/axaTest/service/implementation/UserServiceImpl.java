package com.codeid.axaTest.service.implementation;

import java.util.List;

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
    public List<UserDto> findAll() {
        return userRepo.findAll().stream()
                .map(u -> new UserDto(
                        u.getUserId(),
                        u.getUsername(),
                        u.getPassword(),
                        u.getRole() != null ? u.getRole().getRoleId() : null
                ))
                .toList();
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
        return new UserDto(user.getUserId(), user.getUsername(), user.getPassword(),
                user.getRole() != null ? user.getRole().getRoleId() : null);
    }

    @Override
    public UserDto save(UserDto dto) {
        Role role = roleRepo.findById(dto.roleId())
                .orElseThrow(() -> new RuntimeException("Role with ID " + dto.roleId() + " not found"));

        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(role);
        user = userRepo.save(user);

        return new UserDto(user.getUserId(), user.getUsername(), user.getPassword(), user.getRole().getRoleId());
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));

        Role role = roleRepo.findById(dto.roleId())
                .orElseThrow(() -> new RuntimeException("Role with ID " + dto.roleId() + " not found"));

        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setRole(role);
        userRepo.save(user);

        return new UserDto(user.getUserId(), user.getUsername(), user.getPassword(), user.getRole().getRoleId());
    }

    @Override
    public void delete(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        userRepo.deleteById(id);
    }
}
