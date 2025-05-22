package com.eshopper_backend_final.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eshopper_backend_final.model.dto.OrderDetailDto;
import com.eshopper_backend_final.model.entity.OrderDetail;
import com.eshopper_backend_final.model.entity.OrderDetailId;
import com.eshopper_backend_final.model.entity.Orders;
import com.eshopper_backend_final.model.entity.Products;
import com.eshopper_backend_final.repository.OrderDetailRepository;
import com.eshopper_backend_final.repository.OrdersRepository;
import com.eshopper_backend_final.repository.ProductsRepository;
import com.eshopper_backend_final.service.OrderDetailService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;
    

    public static OrderDetailDto mapToDto(OrderDetail orderDetail){
        return new OrderDetailDto(
            orderDetail.getOrders().getOrderId(),
            orderDetail.getProducts().getProductId(),
            orderDetail.getPrice(),
            orderDetail.getQuantity(),
            orderDetail.getDiscount(),
            orderDetail.getVoucher()
        );
    }

    @Override
    public List<OrderDetailDto>findAll(){
        log.debug("Request to fetch all cart items");
        return this.orderDetailRepository.findAll().stream()
        .map(OrderDetailServiceImpl::mapToDto)
        .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDto findById(OrderDetailId id) {
    log.debug("Request to fetch cart item by id: {}", id);
    return orderDetailRepository.findById(id)
        .map(OrderDetailServiceImpl::mapToDto)
        .orElseThrow(() -> new EntityNotFoundException("cart item not found with id " + id));
    }

    @Override
    public OrderDetailDto save(OrderDetailDto dto) {
        log.debug("Request to save cart item: {}", dto);

        Orders orders = ordersRepository.findById(dto.getOrderId())
        .orElseThrow(() -> new EntityNotFoundException("cart not found"));

        Products products = productsRepository.findById(dto.getProductId())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        BigDecimal price = orders.getTotalAmount();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(new OrderDetailId(dto.getOrderId(), dto.getProductId()));
        orderDetail.setOrders(orders);
        orderDetail.setProducts(products);
        orderDetail.setPrice(price);
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setDiscount(dto.getDiscount());

        return mapToDto(orderDetailRepository.save(orderDetail));
}

@Override
public OrderDetailDto update(OrderDetailId id, OrderDetailDto dto) {
    log.debug("Request to update cart item: {}", id);
    OrderDetail orderDetail = orderDetailRepository.findById(id)
    .orElseThrow(() -> new EntityNotFoundException("order detail not found with id " + id));

        Orders orders = ordersRepository.findById(dto.getOrderId())
        .orElseThrow(() -> new EntityNotFoundException("cart not found"));

        Products products = productsRepository.findById(dto.getProductId())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        BigDecimal price = orders.getTotalAmount();

        orderDetail.setOrders(orders);
        orderDetail.setProducts(products);
        orderDetail.setPrice(price);
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setDiscount(dto.getDiscount());

        return mapToDto(orderDetailRepository.save(orderDetail));
}

@Override
public void delete(OrderDetailId id) {
    log.debug("Request to delete cart item with id: {}", id);

    var orderDetail = orderDetailRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cart item not found with id " + id));

    orderDetailRepository.delete(orderDetail);
}


    @Override
    public List<OrderDetailDto> findAll(Pageable pageable) {
        return this.orderDetailRepository.findAll(pageable).stream().map(OrderDetailServiceImpl::mapToDto).toList();
    }

    @Override
public List<OrderDetailDto> findByOrderId(Long orderId) {
    return orderDetailRepository.findByOrders_OrderId(orderId)
        .stream().map(OrderDetailServiceImpl::mapToDto).toList();
}

@Override
public void deleteByOrderId(Long orderId) {
    List<OrderDetail> items = orderDetailRepository.findByOrders_OrderId(orderId);
    orderDetailRepository.deleteAll(items);
}
}

