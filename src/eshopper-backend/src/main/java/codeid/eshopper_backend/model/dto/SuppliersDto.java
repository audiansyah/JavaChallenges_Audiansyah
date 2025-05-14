package codeid.eshopper_backend.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuppliersDto {
    private Long supplierId;

    @Size(max = 50, message = "Length value must not exceeded than 50")
    private String companyName;
}
