package com.codeid.eshopper_schema_oe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="shippers", schema = "oe")
public class Shipper {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipper_id")
    private Long shipperId;

    @Column(name="company_name")
    @Size(max = 40, message = "company_name maksimal 40 karakter")
    @NotBlank(message="company_name Harus diisi")
    private String companyName;

    @Column(name="phone")
    @Size(max = 24, message = "phone maksimal 24 karakter")
    @NotBlank(message="phone Harus diisi")
    private String phone;

    public Shipper(){

    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
