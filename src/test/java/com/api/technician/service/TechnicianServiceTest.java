package com.api.technician.service;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Technician;
import com.api.technician.repository.TechnicianRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnicianServiceTest {
    @Mock
    private TechnicianRepository technicianRepository;
    @InjectMocks
    private TechnicianService technicianService;
    @Test
    void createTechnician() {
        // Arrange
        TechnicianRepository technicianRepository = mock(TechnicianRepository.class);
        TechnicianService technicianService = new TechnicianService(technicianRepository);
        Technician mockTechnician = new Technician(); // Mocking the technician object
        Technician savedTechnician = new Technician(); // Mocking the saved technician object

        // Mocking the repository method to return the saved technician
        when(technicianRepository.save(any())).thenReturn(savedTechnician);

        // Act
        ResponseEntity<ApiResponse<Technician>> responseEntity = technicianService.createTechnician(mockTechnician);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Tecnico creado correctamente", responseEntity.getBody().getMessage());
        assertEquals(savedTechnician, responseEntity.getBody().getData());
    }
}