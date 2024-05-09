package com.api.technician.controller;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Services;
import com.api.technician.repository.ServicesRepository;
import com.api.technician.service.ServicesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicesControllerTest {
    @Mock
    private ServicesService servicesService;
    @Mock
    private ServicesRepository servicesRepository;
    @InjectMocks
    private ServicesController servicesController;

    @Test
    void createServices() {

        Services services = new Services();
        ApiResponse<Services> expectedResponse = new ApiResponse<>(services, "success", "Servicio creado satisfactoriamente");
        when(servicesService.createServices(services)).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        ResponseEntity<ApiResponse<Services>> actualResponse = servicesController.createServices(services);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());

    }

    @Test
    void getServicesById() {
        Integer id = 1;
        Services services = new Services();
        ApiResponse<Services> expectedResponse = new ApiResponse<>(services, "success", "Servicio obtenido satisfactoriamente");
        when(servicesService.getServicesId(id)).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        ResponseEntity<ApiResponse<Services>> actualResponse = servicesController.getServicesById(id);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    void getAllServices() {
        int page = 0;
        int size = 10;
        List<Services> servicesList = Arrays.asList(new Services(), new Services());
        Page<Services> servicesPage = new PageImpl<>(servicesList);
        ApiResponse<Page<Services>> expectedResponse = new ApiResponse<>(servicesPage, "success", "Servicios obtenidos satisfactoriamente");
        when(servicesService.getAllServices(PageRequest.of(page, size))).thenReturn(servicesPage);

        ResponseEntity<ApiResponse<Page<Services>>> actualResponse = servicesController.getAllServices(page, size);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    void getServicesByFilters(){
        Integer technicianId = 1;
        String startDate = "13/05/2024";
        String endDate = "14/05/2024";
        List<Services> servicesList = Arrays.asList(new Services(), new Services());
        ApiResponse<List<Services>> expectedResponse = new ApiResponse<>(servicesList, "success", "Servicios obtenidos satisfactoriamente");
        when(servicesRepository.findServicesByFilters(technicianId, startDate, endDate)).thenReturn(servicesList);

        ResponseEntity<ApiResponse<List<Services>>> actualResponse = servicesController.getServicesByFilters(technicianId, startDate, endDate);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }
}