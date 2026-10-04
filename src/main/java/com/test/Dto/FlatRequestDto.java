package com.test.Dto;

public class FlatRequestDto {
    int flatnumber;
    String wing;
    String floortype;
    String status;
    int societyId;

    public int getFlatnumber() { return flatnumber; }
    public void setFlatnumber(int flatnumber) { this.flatnumber = flatnumber; }

    public String getWing() { return wing; }
    public void setWing(String wing) { this.wing = wing; }

    public String getFloortype() { return floortype; }
    public void setFloortype(String floortype) { this.floortype = floortype; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getSocietyId() { return societyId; }
    public void setSocietyId(int societyId) { this.societyId = societyId; }
}
