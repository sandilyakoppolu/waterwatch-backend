package com.waterwatch.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.waterwatch.backend.model.Complaint;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, String> {

    // 🔹 Summary Queries
    @Query("SELECT c.city, COUNT(c) FROM Complaint c GROUP BY c.city")
    List<Object[]> countByCity();

    @Query("SELECT c.area, COUNT(c) FROM Complaint c GROUP BY c.area")
    List<Object[]> countByArea();

    // 🔥 NEW METHOD (User-specific complaints)
    List<Complaint> findByUserEmail(String userEmail);
}
