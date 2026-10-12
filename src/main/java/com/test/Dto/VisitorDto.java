package com.test.Dto;

import java.time.LocalDateTime;

public class VisitorDto {

    private int visid;
    private String name;
    private String mobile;
    private String email;
    private String purpose;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private int flatId;
    private int residentId;

    public VisitorDto() {
        super();
    }

    public VisitorDto(int visid, String name, String mobile, String email,
            String purpose, LocalDateTime entryDate, LocalDateTime exitDate,
            int flatId, int residentId) {
        super();
        this.visid = visid;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.purpose = purpose;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.flatId = flatId;
        this.residentId = residentId;
    }

    public int getVisid() {
        return visid;
    }

    public void setVisid(int visid) {
        this.visid = visid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public int getFlatId() {
        return flatId;
    }

    public void setFlatId(int flatId) {
        this.flatId = flatId;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }
}
