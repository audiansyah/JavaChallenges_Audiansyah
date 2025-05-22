package com.eshopper_backend_final.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank", schema="fintech")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @Column(length = 4)
    private String bankCode;

    private String bankName;
}

