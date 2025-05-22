package com.eshopper_backend_final.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eshopper_backend_final.model.dto.UsersDto;
import com.eshopper_backend_final.model.entity.Users;
import com.eshopper_backend_final.repository.UsersRepository;
import com.eshopper_backend_final.service.UsersService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public static UsersDto mapToDto(Users users) {
        return new UsersDto(
            users.getUserId(),
            users.getUserName(),
            users.getUserEmail(),
            users.getUserPassword(),
            users.getUserHandphone()
        );
    }

    public static Users mapToEntity(UsersDto usersDto) {
        return new Users(
            usersDto.getUserId(),
            usersDto.getUserName(),
            usersDto.getUserEmail(),
            usersDto.getUserPassword(),
            usersDto.getUserHandphone()
        );
    }

    @Override
    public List<UsersDto> findAll() {
        return this.usersRepository.findAll().stream().map(UserServiceImpl::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UsersDto findById(Long id) {
        return mapToDto(usersRepository.findById(id).orElseThrow());
    }

    @Override
    public UsersDto save(UsersDto dto) {
        Users users = mapToEntity(dto);
        return mapToDto(usersRepository.save(users));
    }

    @Override
    public UsersDto update(Long id, UsersDto dto) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found with id " + id));
        users.setUserName(dto.getUserName());
        users.setUserEmail(dto.getUserEmail());
        users.setUserPassword(dto.getUserPassword());
        users.setUserHandphone(dto.getUserHandphone());
        return mapToDto(usersRepository.save(users));
    }

    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}

