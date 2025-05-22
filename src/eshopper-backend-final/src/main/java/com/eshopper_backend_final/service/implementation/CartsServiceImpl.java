package com.eshopper_backend_final.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshopper_backend_final.model.dto.CartsDto;
import com.eshopper_backend_final.model.entity.Carts;
import com.eshopper_backend_final.model.entity.Users;
import com.eshopper_backend_final.repository.CartsRepository;
import com.eshopper_backend_final.repository.UsersRepository;
import com.eshopper_backend_final.service.CartsService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartsServiceImpl implements CartsService{
    private final CartsRepository cartsRepository;
    private final UsersRepository usersRepository;

    public static CartsDto mapToDto(Carts carts){
        return new CartsDto(
            carts.getCartId(),
            carts.getUsers().getUserId(),
            carts.getCartItems().stream().map(CartItemsServiceImpl::mapToDto).toList()
        );
    }

    @Override
    public List<CartsDto> findAll(){
        log.debug("request fetching data product");
        return this.cartsRepository.findAll().stream()
        .map(CartsServiceImpl::mapToDto)
        .collect(Collectors.toList());
    }

    @Override
    public CartsDto findById(Long id){
        log.debug("Request to get product : {}", id);
        return this.cartsRepository.findById(id)
        .map(CartsServiceImpl::mapToDto)
        .orElseThrow(()-> new EntityNotFoundException("cart not found with id "+id));
    }

    @Override
    public CartsDto save(CartsDto dto){
        log.debug("request to save product : {}", dto);

        Users users = usersRepository.findById(dto.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("cart not found"));

        Carts carts = new Carts();
        carts.setCartId(dto.getCartId());
        carts.setUsers(users);

        return mapToDto(cartsRepository.save(carts));
    }

    @Override
    public CartsDto update(Long id, CartsDto entity){
        log.debug("request to update cart : {}", id);

        Carts carts = this.cartsRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("cart not found with id " + id));

        Users users = this.usersRepository.findById(entity.getUserId())
        .orElseThrow(() -> new EntityNotFoundException("user not found"));

        carts.setCartId(entity.getCartId());
        carts.setUsers(users);

        this.cartsRepository.save(carts);
        return mapToDto(carts);

    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete product : {}", id);

        var carts = this.cartsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find cart with id " + id));

        this.cartsRepository.delete(carts);
    }
    

    @Override
    public List<CartsDto> findAll(Pageable pageable) {
        return this.cartsRepository.findAll(pageable).stream().map(CartsServiceImpl::mapToDto).toList();
    }
}

