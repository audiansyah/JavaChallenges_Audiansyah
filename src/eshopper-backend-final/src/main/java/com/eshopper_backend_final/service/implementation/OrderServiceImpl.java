package com.eshopper_backend_final.service.implementation;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eshopper_backend_final.model.dto.OrdersDto;
import com.eshopper_backend_final.model.entity.Bank;
import com.eshopper_backend_final.model.entity.Employee;
import com.eshopper_backend_final.model.entity.Locations;
import com.eshopper_backend_final.model.entity.Orders;
import com.eshopper_backend_final.model.entity.Users;
import com.eshopper_backend_final.repository.BankRepository;
import com.eshopper_backend_final.repository.EmployeesRepository;
import com.eshopper_backend_final.repository.LocationRepository;
import com.eshopper_backend_final.repository.OrdersRepository;
import com.eshopper_backend_final.repository.UsersRepository;
import com.eshopper_backend_final.service.OrderService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDetailServiceImpl orderDetailServiceImpl;

    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final EmployeesRepository employeesRepository;
    private final LocationRepository locationsRepository;
    private final BankRepository bankRepository;

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
                order.getOrderDetail().stream().map(OrderDetailServiceImpl::mapToDto).toList()
        );
    }


    @Transactional
    @Override
    public OrdersDto save(OrdersDto dto) {
        log.debug("Request to create new order: {}", dto);

        Users user = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Employee employee = employeesRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Locations location = locationsRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));

        Bank bank = null; if (dto.getBankCode() != null) {bank = bankRepository.findById(dto.getBankCode())
                .orElseThrow(() -> new EntityNotFoundException("Bank not found with code: " + dto.getBankCode()));
        }

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
        
        Orders savedOrder = ordersRepository.save(order);
        savedOrder.setTransacNo("TR" + savedOrder.getOrderId());
        savedOrder.setTransacDate(LocalDateTime.now());
        
        savedOrder = ordersRepository.save(savedOrder);

        //to do add data dari cart ke order detail, stock harus berkurang


        return mapToDto(savedOrder);
}

@Override
    @Transactional
    public OrdersDto update(Long id, OrdersDto dto) {
        Orders Order = ordersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Order.setFreight(dto.getFreight());
        Order.setShipName(dto.getShipName());
        Order.setPaymentType(dto.getPaymentType());
        Order.setCardNo(dto.getCardNo());
        Order.setTransacNo(dto.getTransacNo());
        Order.setTransacDate(dto.getTransacDate());
        Order.setRefNo(dto.getRefNo());

        Users user = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Employee employee = employeesRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        Locations location = locationsRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
        Bank bank = null; if (dto.getBankCode() != null) {bank = bankRepository.findById(dto.getBankCode())
                .orElseThrow(() -> new EntityNotFoundException("Bank not found with code: " + dto.getBankCode()));
        }
        Order.setUsers(user);
        Order.setEmployee(employee);
        Order.setLocations(location);
        Order.setBank(bank);

        return mapToDto(ordersRepository.save(Order));
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
