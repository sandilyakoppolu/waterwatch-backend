package com.waterwatch.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.waterwatch.backend.model.Complaint;
import com.waterwatch.backend.repository.ComplaintRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository repository;

    // Save Complaint (WITH ANDHRA PRADESH LOCATION CHECK)
    public Complaint saveComplaint(Complaint complaint) {

        Double lat = complaint.getLatitude();
        Double lon = complaint.getLongitude();

        if (lat == null || lon == null) {
            throw new RuntimeException("Location not provided");
        }

        // Andhra Pradesh approximate boundary
        if (lat < 12.5 || lat > 19.5 || lon < 76.5 || lon > 84.5) {
            throw new RuntimeException("Complaints allowed only within Andhra Pradesh");
        }

        return repository.save(complaint);
    }

    // Get Complaint by ID
    public Optional<Complaint> getComplaintById(String id) {
        return repository.findById(id);
    }

    // Get All Complaints
    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    // Update Status
    public Complaint updateStatus(String id, String status) {
        Complaint complaint = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        complaint.setStatus(status);
        return repository.save(complaint);
    }

    public List<Object[]> getComplaintCountByCity() {
        return repository.countByCity();
    }

    public List<Object[]> getComplaintCountByArea() {
        return repository.countByArea();
    }

    public List<Complaint> getComplaintsByUserEmail(String email) {
        return repository.findByUserEmail(email);
    }
}