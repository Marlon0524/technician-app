package com.api.technician.service;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Services;
import com.api.technician.model.Technician;
import com.api.technician.repository.ServicesRepository;
import com.api.technician.repository.TechnicianRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicesServiceTest {
    @Mock
    private ServicesRepository servicesRepository;
    @Mock
    private TechnicianRepository technicianRepository;
    @InjectMocks
    private ServicesService servicesService;
    @Test
    void createServices() {
        // Arrange
        Services inputServices = new Services();
        Technician technician = new Technician();
        technician.setTechnician_id(1);
        inputServices.setTechnician(technician);

        when(technicianRepository.findById(1)).thenReturn(Optional.of(technician));
        when(servicesRepository.save(inputServices)).thenReturn(inputServices);

        // Act
        ResponseEntity<ApiResponse<Services>> responseEntity = servicesService.createServices(inputServices);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("servicio creado con éxito", responseEntity.getBody().getMessage());
        assertEquals(inputServices, responseEntity.getBody().getData());
    }
    @Test
    void getServicesId(){
        // Arrange
        Services expectedService = new Services();
        when(servicesRepository.findById(1)).thenReturn(Optional.of(expectedService));

        // Act
        ResponseEntity<ApiResponse<Services>> responseEntity = servicesService.getServicesId(1);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Servicio encontrado", responseEntity.getBody().getMessage());
        assertEquals(expectedService, responseEntity.getBody().getData());
    }
    @Test
    void getAllServices(){
        // Arrange
        Pageable pageable = Pageable.unpaged();
        List<Services> servicesList = new ArrayList<>();
        Page<Services> expectedPage = new PageImpl<>(servicesList);
        when(servicesRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Services> resultPage = servicesService.getAllServices(pageable);

        // Assert
        assertEquals(expectedPage, resultPage);
    }
    @Test
    void filterServices(){
        // Arrange
        List<Services> expectedServicesList = new ArrayList<>();
        when(servicesRepository.findServicesByFilters(anyInt(), anyString(), anyString())).thenReturn(expectedServicesList);

        // Act
        List<Services> resultServicesList = servicesService.filterServices(1, "13/05/2024", "14/05/2024");

        // Assert
        assertEquals(expectedServicesList, resultServicesList);
    }
}