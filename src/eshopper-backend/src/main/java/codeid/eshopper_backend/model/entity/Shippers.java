package codeid.eshopper_backend.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor //empty constructor
@AllArgsConstructor //semua attribute departmentid & departmentName masuk ke constructor
@RequiredArgsConstructor // hanya attribute yg diberi annotatsi @NonNull atau private final
@Entity
@Table(name="shippers",schema = "oe")
public class Shippers extends AbstractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipper_id")
    private Long shipperId;

    @Nonnull
    @Column(name = "company_name")
    private String companyName;

    @Nonnull
    @Column(name = "phone")
    private String phone;
}
