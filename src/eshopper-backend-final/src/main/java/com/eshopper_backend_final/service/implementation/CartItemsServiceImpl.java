package com.eshopper_backend_final.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshopper_backend_final.model.dto.CartItemsDto;
import com.eshopper_backend_final.model.entity.CartItems;
import com.eshopper_backend_final.model.entity.CartItemsId;
import com.eshopper_backend_final.model.entity.Carts;
import com.eshopper_backend_final.model.entity.Products;
import com.eshopper_backend_final.repository.CartItemsRepository;
import com.eshopper_backend_final.repository.CartsRepository;
import com.eshopper_backend_final.repository.ProductsRepository;
import com.eshopper_backend_final.service.CartItemsService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartItemsServiceImpl implements CartItemsService{
    private final CartItemsRepository cartItemsRepository;
    private final ProductsRepository productsRepository;
    private final CartsRepository cartsRepository;

    public static CartItemsDto mapToDto(CartItems cartItems){
        return new CartItemsDto(
            cartItems.getCarts().getCartId(),
            cartItems.getProducts().getProductId(),
            cartItems.getQuantity(),
            cartItems.getUnitPrice(),
            cartItems.getDiscount()
        );
    }

    @Override
    public List<CartItemsDto>findAll(){
        log.debug("Request to fetch all cart items");
        return this.cartItemsRepository.findAll().stream()
        .map(CartItemsServiceImpl::mapToDto)
        .collect(Collectors.toList());
    }

    @Override
    public CartItemsDto findById(CartItemsId id) {
    log.debug("Request to fetch cart item by id: {}", id);
    return cartItemsRepository.findById(id)
        .map(CartItemsServiceImpl::mapToDto)
        .orElseThrow(() -> new EntityNotFoundException("cart item not found with id " + id));
    }

@Override
public CartItemsDto save(CartItemsDto dto) {
    log.debug("Request to create cart item: {}", dto);

    Carts carts = cartsRepository.findById(dto.getCartId())
        .orElseThrow(() -> new EntityNotFoundException("cart not found"));

    Products products = productsRepository.findById(dto.getProductId())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));


    BigDecimal unitPrice = products.getUnitPrice();

    CartItems cartItems = new CartItems();
    cartItems.setId(new CartItemsId(dto.getCartId(), dto.getProductId()));
    cartItems.setCarts(carts);
    cartItems.setProducts(products);
    cartItems.setQuantity(dto.getQuantity());
    cartItems.setUnitPrice(unitPrice);
    cartItems.setDiscount(dto.getDiscount());

    return mapToDto(cartItemsRepository.save(cartItems));
}


@Override
public CartItemsDto update(CartItemsId id, CartItemsDto dto) {
    log.debug("Request to update cart item with id: {}", id);

    var cartItems = cartItemsRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("cart item not found with id " + id));

    Carts carts = cartsRepository.findById(dto.getCartId())
        .orElseThrow(() -> new EntityNotFoundException("cart not found"));

    Products products = productsRepository.findById(dto.getProductId())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    cartItems.setCarts(carts);
    cartItems.setProducts(products);
    cartItems.setQuantity(dto.getQuantity());
    cartItems.setUnitPrice(dto.getUnitPrice());
    cartItems.setDiscount(dto.getDiscount());

    return mapToDto(cartItemsRepository.save(cartItems));
}

@Override
public void delete(CartItemsId id) {
    log.debug("Request to delete cart item with id: {}", id);

    var cartItems = cartItemsRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cart item not found with id " + id));

    cartItemsRepository.delete(cartItems);
}


    @Override
    public List<CartItemsDto> findAll(Pageable pageable) {
        return this.cartItemsRepository.findAll(pageable).stream().map(CartItemsServiceImpl::mapToDto).toList();
    }

    @Override
public List<CartItemsDto> findByCartId(Long cartId) {
    return cartItemsRepository.findByCarts_CartId(cartId)
        .stream().map(CartItemsServiceImpl::mapToDto).toList();
}

@Override
public void deleteByCartId(Long cartId) {
    List<CartItems> items = cartItemsRepository.findByCarts_CartId(cartId);
    cartItemsRepository.deleteAll(items);
}

}