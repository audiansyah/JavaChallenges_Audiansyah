package codeid.eshopper_backend.model.entity;

import codeid.eshopper_backend.model.dto.CategoryDto;
import codeid.eshopper_backend.model.dto.SuppliersDto;
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

    @Column (name = "quantity_per_unit")
    private String quantityPerUnit;

    @Column (name = "unit_price")
    private double unitPrice;

    @Column (name = "units_in_stock")
    private int unitsInStock;

    @Column (name = "units_on_order")
    private int unitsOnOrder;

    @Column (name = "reorder_level")
    private int reorderLevel;

    @Column (name = "discontinued")
    private int discontinued;

    @Column(name="picture",nullable = true)
    private String picture;
}
