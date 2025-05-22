package com.eshopper_backend_final.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="products", schema = "oe")
public class Products extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column (name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn (name = "supplier_id")
    private Suppliers suppliers;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

    @Column (name = "unit_price")
    private BigDecimal unitPrice;

    @Column (name = "units_in_stock")
    private int unitsInStock;

    @Column(name="picture",nullable = true)
    private String picture;
}
