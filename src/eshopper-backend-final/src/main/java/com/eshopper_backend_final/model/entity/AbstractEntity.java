package com.eshopper_backend_final.model.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {
    @Column(name="created_date", nullable = false)
    private Instant createDate = Instant.now();

    @Column(name="modified_date")
    private Instant modifiedDate;
}
