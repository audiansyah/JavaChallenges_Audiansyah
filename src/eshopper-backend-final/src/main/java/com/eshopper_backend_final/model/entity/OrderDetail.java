package com.eshopper_backend_final.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details", schema="oe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @EmbeddedId
    private OrderDetailId id;


    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Orders orders;


    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(name="unit_price")
    private BigDecimal price;
    private Long quantity;
    private BigDecimal discount;
    private BigDecimal voucher;
}

