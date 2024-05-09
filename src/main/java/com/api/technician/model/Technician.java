package com.api.technician.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Technician {
    @Id
    @GeneratedValue
    private Integer technician_id;
    @Size(max = 30, message = "El nombre no puede contener más de 30 caracteres")
    private String name;
}
