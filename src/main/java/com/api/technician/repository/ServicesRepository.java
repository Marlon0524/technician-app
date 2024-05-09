package com.api.technician.repository;

import com.api.technician.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {
    @Query(value = "SELECT * FROM techniciandb.services s\n" +
            "WHERE \n" +
            "(s.technician_id = ?1 OR ?1 IS NULL) AND\n" +
            "(s.start_date = ?2 OR ?2 IS NULL) AND \n" +
            "(s.end_date = ?3 OR ?3 IS NULL);", nativeQuery = true)
    List<Services> findServicesByFilters(
            Integer technicianId, String startDate, String endDate);
}
