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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart_items", schema = "oe")
public class CartItems extends AbstractEntity {
    

    @EmbeddedId
    private CartItemsId id;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Carts carts;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(name="quantity")
    private Long quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "discount")
    private BigDecimal discount;
}
