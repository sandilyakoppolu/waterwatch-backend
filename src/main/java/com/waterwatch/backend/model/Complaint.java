package com.waterwatch.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Complaint {

    @Id
    private String complaintId;

    private String issueType;
    private String city;
    private String area;
    private String address;
    private String urgency;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private Double latitude;
    private Double longitude;

    // 🔥 NEW FIELD (Important)
    private String userEmail;

    @PrePersist
    public void beforeSave() {
        this.complaintId = "WW-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        this.status = "Pending";
        this.createdAt = LocalDateTime.now();
    }

    public String getComplaintId() { return complaintId; }

    public String getIssueType() { return issueType; }
    public void setIssueType(String issueType) { this.issueType = issueType; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    // 🔥 NEW GETTER + SETTER
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
