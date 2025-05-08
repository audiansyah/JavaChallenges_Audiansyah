package codeid.eshopper_backend.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippersDto {
    private Long shipperId;

    @Size(max = 50, message = "Length value must not exceeded than 50")
    private String companyName;
    
    @Size(max = 24, message = "Phone value must not exceeded than 24")
    private String phone;
}
