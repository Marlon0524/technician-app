package com.api.technician.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Services {

    @Id
    @GeneratedValue
    private Integer service_id;
    @Size(max = 30, message = "La dirección no puede tener más de 30 caracteres")
    private String adress;
    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String startDate;
    private String endDate;
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

}
