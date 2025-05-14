package codeid.eshopper_backend.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductsDto {
    private Long productId;

    @Size(max = 40, message = "Length value must not exceeded than 40")
    private String productName;

    private SuppliersDto suppliers;

    private CategoryDto category;

    @Size(max = 20, message = "Length value must not exceeded than 20")
    private String quantityPerUnit;

    private double unitPrice;

    private int unitsInStock;

    private int unitsOnOrder;

    private int reorderLevel;

    private int discontinued;

    private String picture;
}
