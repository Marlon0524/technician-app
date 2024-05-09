package com.api.technician.controller;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Technician;
import com.api.technician.service.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/technician")
@RequiredArgsConstructor
public class TechnicianController {
    private final TechnicianService technicianService;

    @PostMapping
    public ResponseEntity<ApiResponse<Technician>> createTechnician (@RequestBody Technician technician){
        return technicianService.createTechnician(technician);
    }
}
