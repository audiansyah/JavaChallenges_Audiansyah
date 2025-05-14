package codeid.eshopper_backend.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImageDto {
    private Long productPhotoId;

    private String fileName;

    private Double fileSize;

    @Size(max=15,message = "Lenght maximal 15")
    private String fileType;

    private String textUri;

    private Long productsId;
}
