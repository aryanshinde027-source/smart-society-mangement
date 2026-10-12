
package com.test.Entities;

import jakarta.persistence.*;

@Entity
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilid;

    @Column(nullable = false)
    private String facilname;
    private String type;
    private String description;
    private String location;
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "society_id", nullable = false)
    private Society societyId;

    public Facility() {
    }

    public int getFacilid() {
        return facilid;
    }

    public void setFacilid(int facilid) {
        this.facilid = facilid;
    }

    public String getFacilname() {
        return facilname;
    }

    public void setFacilname(String facilname) {
        this.facilname = facilname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Society getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Society societyId) {
        this.societyId = societyId;
    }
}
