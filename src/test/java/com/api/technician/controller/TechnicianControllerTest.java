package com.api.technician.controller;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Technician;
import com.api.technician.repository.TechnicianRepository;
import com.api.technician.service.TechnicianService;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TechnicianControllerTest {
    @Mock
    private TechnicianService technicianService;
    @Mock
    private TechnicianRepository technicianRepository;
    @InjectMocks
    private TechnicianController technicianController;
    @Test
    void createTechnician() {
        TechnicianService technicianService = mock(TechnicianService.class);
        TechnicianController technicianController = new TechnicianController(technicianService);
        Technician mockTechnician = new Technician();

        when(technicianService.createTechnician(any())).thenReturn(ResponseEntity.ok(new ApiResponse<>(mockTechnician,"success","Tecnico creado satisfactoriamente")));

        ResponseEntity<ApiResponse<Technician>> responseEntity = technicianController.createTechnician(mockTechnician);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Tecnico creado satisfactoriamente", responseEntity.getBody().getMessage());
        assertEquals(mockTechnician, responseEntity.getBody().getData());
        // Verifica que el método de servicio fue llamado una vez con el argumento correcto
        verify(technicianService, times(1)).createTechnician(mockTechnician);
    }
}