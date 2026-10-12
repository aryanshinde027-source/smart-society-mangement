
package com.test.Dto;

public class FacilityDto {

    private int facilid;
    private String facilname;
    private String type;
    private String description;
    private String location;
    private String status;
    private int societyId;

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

    public int getSocietyId() {
        return societyId;
    }

    public void setSocietyId(int societyId) {
        this.societyId = societyId;
    }
}
