package com.waterwatch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.waterwatch.backend.model.Complaint;
import com.waterwatch.backend.service.ComplaintService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    // Create Complaint
    @PostMapping
    public Complaint createComplaint(@RequestBody Complaint complaint) {
        return service.saveComplaint(complaint);
    }

    // Get Complaint by ID
    @GetMapping("/{id}")
    public Complaint getComplaint(@PathVariable String id) {
        Optional<Complaint> complaint = service.getComplaintById(id);
        return complaint.orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    // Get All Complaints (For Admin)
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return service.getAllComplaints();
    }

    // Update Complaint Status (For Admin)
    @PutMapping("/{id}/status")
    public Complaint updateStatus(
            @PathVariable String id,
            @RequestParam String status) {

        return service.updateStatus(id, status);
    }
 // 🔥 Get Complaints by User Email
    @GetMapping("/user/{email}")
    public List<Complaint> getComplaintsByUser(@PathVariable String email) {
        return service.getComplaintsByUserEmail(email);
    }

    
    @GetMapping("/summary/city")
    public List<Object[]> getSummaryByCity() {
        return service.getComplaintCountByCity();
    }

    @GetMapping("/summary/area")
    public List<Object[]> getSummaryByArea() {
        return service.getComplaintCountByArea();
    }

}
