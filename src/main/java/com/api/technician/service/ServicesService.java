package com.api.technician.service;

import com.api.technician.model.ApiResponse;
import com.api.technician.model.Services;
import com.api.technician.model.Technician;
import com.api.technician.repository.ServicesRepository;
import com.api.technician.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesRepository servicesRepository;
    private final TechnicianRepository technicianRepository;

    public ResponseEntity<ApiResponse<Services>> createServices(Services services) {
        try {
            //Obtener el Id del técnico desde el producto
            Integer technicianId = services.getTechnician().getTechnician_id();

            //verificar si el técnico existe en la base de datos
            Optional<Technician> existingTechnicianOptional = technicianRepository.findById(technicianId);
            if (existingTechnicianOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(null, "error", "tecnico no encontrado con el ID:" + technicianId));
            }
            //obtener la instancia existente de tecnico en la bd
            Technician existingTechnician = existingTechnicianOptional.get();
            //Asignar el tecnico existente al servicio
            services.setTechnician(existingTechnician);
            //guardar el servicio en la bd
            Services newServices = servicesRepository.save(services);
            //construir el objeto apiresponse con el servicio creado
            ApiResponse<Services> response = new ApiResponse<>(newServices, "success", "servicio creado con éxito");
            //devolver el responseentity con el apiresponse
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            //manejar cualquier excepción que pueda ocurrir
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "error", "error al crear el servicio " + e.getMessage()));
        }
    }

    public ResponseEntity<ApiResponse<Services>> getServicesId(@PathVariable Integer id) {
        try {
            Optional<Services> optionalServices = servicesRepository.findById(id);
            if (optionalServices.isPresent()) {
                Services services = optionalServices.get();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse<>(services, "success", "Servicio encontrado"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "error", "Servicio no encontrado con el id: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "error", "error al obtener el servicio" + e.getMessage()));
        }
    }
    public Page<Services> getAllServices(Pageable pageable) {
        return servicesRepository.findAll(pageable);
    }
    public List<Services> filterServices(Integer technicianId, String startDate, String endDate) {
        return servicesRepository.findServicesByFilters(technicianId,startDate,endDate);
    }

}
