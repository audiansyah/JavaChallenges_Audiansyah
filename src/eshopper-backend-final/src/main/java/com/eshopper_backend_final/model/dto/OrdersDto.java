package com.eshopper_backend_final.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrdersDto {
    private long orderId;
    private Long userId;
    private Long employeeId;
    private Long locationId;
    private String bankCode;
    private String shipName;
    private String paymentType;
    private BigDecimal freight;
    private String cardNo;
    private String transacNo;
    private LocalDateTime transacDate;
    private String refNo;
    private BigDecimal totalDiscount;
    private BigDecimal totalAmount;
    private List<OrderDetailDto> orderDetailDtos;
}

