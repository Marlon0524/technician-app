package com.api.technician.service;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Technician;
import com.api.technician.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    public ResponseEntity<ApiResponse<Technician>> createTechnician(Technician technician) {
        try {
            // Guardar el tecnico en la base de datos
            Technician newTechnician = technicianRepository.save(technician);
            // crear objecto apiresponse con el tecnico creado
            ApiResponse<Technician> response = new ApiResponse<>(newTechnician, "success", "Tecnico creado correctamente");
            //devolver responseentity con el apiresponse
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            //manejar cualquier excepción que pueda ocurrir
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "error", "error al crear el tecnico " + e.getMessage()));
        }
    }
}
