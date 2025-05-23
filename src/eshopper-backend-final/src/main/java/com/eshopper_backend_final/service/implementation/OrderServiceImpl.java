package com.eshopper_backend_final.service.implementation;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eshopper_backend_final.model.dto.OrdersDto;
import com.eshopper_backend_final.model.entity.*;
import com.eshopper_backend_final.repository.*;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements com.eshopper_backend_final.service.OrderService {

        private final OrdersRepository ordersRepository;
        private final UsersRepository usersRepository;
        private final EmployeesRepository employeesRepository;
        private final LocationRepository locationsRepository;
        private final BankRepository bankRepository;
        private final CartsRepository cartRepository;
        private final CartItemsRepository cartItemsRepository;
        private final ProductsRepository productRepository;
        private final OrderDetailRepository orderDetailRepository;

        private static OrdersDto mapToDto(Orders order) {
                return new OrdersDto(
                                order.getOrderId(),
                                order.getUsers().getUserId(),
                                order.getEmployee().getEmployeeId(),
                                order.getLocations().getLocationId(),
                                order.getBank() != null ? order.getBank().getBankCode() : null,
                                order.getShipName(),
                                order.getPaymentType(),
                                order.getFreight(),
                                order.getCardNo(),
                                order.getTransacNo(),
                                order.getTransacDate(),
                                order.getRefNo(),
                                order.getTotalDiscount(),
                                order.getTotalAmount(),
                                order.getProducts().getProductId(),
                                order.getOrderDetail().stream().map(OrderDetailServiceImpl::mapToDto).toList(),
                                order.getOrderDetail().stream().map(od -> od.getProducts().getProductId())
                                                .collect(Collectors.toList()));
        }

        @Transactional
        @Override
        public OrdersDto save(OrdersDto dto) {
                log.debug("Request to create new order: {}", dto);

                // Validasi entitas utama
                Users user = usersRepository.findById(dto.getUserId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "User not found with ID: " + dto.getUserId()));
                Employee employee = employeesRepository.findById(dto.getEmployeeId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Employee not found with ID: " + dto.getEmployeeId()));
                Locations location = locationsRepository.findById(dto.getLocationId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Location not found with ID: " + dto.getLocationId()));
                Bank bank = null;
                if (dto.getBankCode() != null) {
                        bank = bankRepository.findById(dto.getBankCode())
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                        "Bank not found with code: " + dto.getBankCode()));
                }

                // Validasi produk yang dipilih
                if (dto.getProductIds() == null || dto.getProductIds().isEmpty()) {
                        throw new IllegalArgumentException("You must provide at least one product ID to checkout.");
                }

                List<Products> selectedProducts = dto.getProductIds().stream()
                                .map(id -> productRepository.findById(id)
                                                .orElseThrow(() -> new EntityNotFoundException(
                                                                "Product not found: " + id)))
                                .toList();

                Set<Long> supplierIds = selectedProducts.stream()
                                .map(p -> p.getSuppliers().getSupplierId())
                                .collect(Collectors.toSet());

                if (supplierIds.size() > 1) {
                        throw new IllegalArgumentException("You can only order products from one supplier per order.");
                }

                // Buat order baru
                Orders order = new Orders();
                order.setOrderNo("ORD" + System.currentTimeMillis());
                order.setOrderDate(LocalDateTime.now());
                order.setRequiredDate(LocalDateTime.now().plusDays(3));
                order.setShippedDate(LocalDateTime.now());
                order.setFreight(dto.getFreight());
                order.setShipName(dto.getShipName());
                order.setPaymentType(dto.getPaymentType());
                order.setCardNo(dto.getCardNo());
                order.setTransacNo(dto.getTransacNo());
                order.setTransacDate(dto.getTransacDate());
                order.setRefNo(dto.getRefNo());
                order.setTotalDiscount(dto.getTotalDiscount());
                order.setTotalAmount(dto.getTotalAmount());
                order.setUsers(user);
                order.setEmployee(employee);
                order.setLocations(location);
                order.setBank(bank);

                // Set produk utama
                order.setProducts(selectedProducts.get(0));

                Orders savedOrder = ordersRepository.save(order);
                savedOrder.setTransacNo("TR" + savedOrder.getOrderId());
                savedOrder.setTransacDate(LocalDateTime.now());
                savedOrder = ordersRepository.save(savedOrder);

                // Ambil cart user
                Carts cart = cartRepository.findByUsersUserId(dto.getUserId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Cart not found for user ID: " + dto.getUserId()));
                List<CartItems> cartItems = cartItemsRepository.findByCarts_CartId(cart.getCartId());

                // Filter hanya cart item yang dipilih
                Set<Long> selectedProductIds = new HashSet<>(dto.getProductIds());
                List<CartItems> selectedItems = cartItems.stream()
                                .filter(item -> selectedProductIds.contains(item.getProducts().getProductId()))
                                .toList();

                // Simpan detail order
                for (CartItems item : selectedItems) {
                        Products product = item.getProducts();

                        if (product.getUnitsInStock() < item.getQuantity()) {
                                throw new IllegalArgumentException(
                                                "Stock is not enough for product: " + product.getProductName());
                        }

                        OrderDetailId detailId = new OrderDetailId(savedOrder.getOrderId(), product.getProductId());

                        if (orderDetailRepository.existsById(detailId)) {
                                log.warn("OrderDetail already exists for orderId={} and productId={}",
                                                savedOrder.getOrderId(), product.getProductId());
                                continue;
                        }

                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setId(detailId);
                        orderDetail.setOrders(savedOrder);
                        orderDetail.setProducts(product);
                        orderDetail.setQuantity(item.getQuantity());
                        orderDetail.setPrice(product.getUnitPrice());
                        orderDetail.setDiscount(item.getDiscount());

                        orderDetailRepository.save(orderDetail);

                        // Update stok
                        product.setUnitsInStock(product.getUnitsInStock() - item.getQuantity());
                        productRepository.save(product);
                }

                // Hapus hanya item yang di-checkout
                cartItemsRepository.deleteAll(selectedItems);

                return mapToDto(savedOrder);
        }

        @Transactional
        @Override
        public OrdersDto update(Long id, OrdersDto dto) {
                Orders order = ordersRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

                order.setFreight(dto.getFreight());
                order.setShipName(dto.getShipName());
                order.setPaymentType(dto.getPaymentType());
                order.setCardNo(dto.getCardNo());
                order.setTransacNo(dto.getTransacNo());
                order.setTransacDate(dto.getTransacDate());
                order.setRefNo(dto.getRefNo());

                Users user = usersRepository.findById(dto.getUserId())
                                .orElseThrow(() -> new EntityNotFoundException("User not found"));
                Employee employee = employeesRepository.findById(dto.getEmployeeId())
                                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
                Locations location = locationsRepository.findById(dto.getLocationId())
                                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
                Bank bank = null;
                if (dto.getBankCode() != null) {
                        bank = bankRepository.findById(dto.getBankCode())
                                        .orElseThrow(() -> new EntityNotFoundException(
                                                        "Bank not found with code: " + dto.getBankCode()));
                }

                order.setUsers(user);
                order.setEmployee(employee);
                order.setLocations(location);
                order.setBank(bank);

                return mapToDto(ordersRepository.save(order));
        }

        @Override
        public List<OrdersDto> findAll() {
                log.debug("Request to fetch all orders");
                return ordersRepository.findAll().stream()
                                .map(OrderServiceImpl::mapToDto)
                                .collect(Collectors.toList());
        }

        @Override
        public OrdersDto findById(Long id) {
                log.debug("Request to fetch order by id: {}", id);
                return ordersRepository.findById(id)
                                .map(OrderServiceImpl::mapToDto)
                                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
        }

        @Override
        public void delete(Long id) {
                log.debug("Request to delete order by id: {}", id);
                Orders order = ordersRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
                ordersRepository.delete(order);
        }
}
