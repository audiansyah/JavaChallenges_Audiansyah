package codeid.eshopper_backend.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {
    private Long categoryId;

    @Size(max = 50, message = "Length value must not exceeded than 50")
    private String categoryName;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    private String picture;
}
