package com.api.technician.controller;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Services;
import com.api.technician.repository.ServicesRepository;
import com.api.technician.repository.TechnicianRepository;
import com.api.technician.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServicesController {
    private final ServicesService servicesService;
    private final ServicesRepository servicesRepository;
    private static final Logger logger = Logger.getLogger(ServicesController.class.getName());

    @PostMapping
    public ResponseEntity<ApiResponse<Services>> createServices(@RequestBody Services services) {
        return servicesService.createServices(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Services>> getServicesById(@PathVariable Integer id) {
        return servicesService.getServicesId(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Services>>> getAllServices(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        logger.info("Llamada al endpoint GET /api recibida.");
        Page<Services> servicesPage = servicesService.getAllServices(PageRequest.of(page, size));

        if (servicesPage != null && servicesPage.hasContent()) {
            ApiResponse<Page<Services>> response = new ApiResponse<>(servicesPage, "success", "Servicios obtenidos satisfactoriamente");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Page<Services>> response = new ApiResponse<>(null, "error", "No se encontraron servicios");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping("/filters")
    public ResponseEntity<ApiResponse<List<Services>>> getServicesByFilters(
            @RequestParam(required = false) Integer technicianId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        List<Services> services = servicesRepository.findServicesByFilters(technicianId, startDate, endDate);

        if (services.isEmpty()) {
            // Si la lista de servicios está vacía, devuelve un ResponseEntity con un mensaje apropiado
            ApiResponse<List<Services>> response = new ApiResponse<>(null, "error", "No se encontraron servicios con los filtros proporcionados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            // Si se encontraron servicios, devuelve un ResponseEntity con los servicios y un mensaje de éxito
            ApiResponse<List<Services>> response = new ApiResponse<>(services, "success", "Servicios obtenidos satisfactoriamente");
            return ResponseEntity.ok().body(response);
        }
    }

}
