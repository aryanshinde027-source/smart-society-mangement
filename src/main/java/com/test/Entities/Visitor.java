package com.test.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int visid;

    @Column(nullable = false)
    private String name;

    private String mobile;

    private String email;

    private String purpose;

    private LocalDateTime entryDate;

    private LocalDateTime exitDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flat_id", nullable = false)
    private Flat flat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resident_id", nullable = false)
    private Resident resident;

    public Visitor() {
        super();
    }

    public Visitor(int visid, String name, String mobile, String email,
            String purpose, LocalDateTime entryDate, LocalDateTime exitDate,
            Flat flat, Resident resident) {
        super();
        this.visid = visid;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.purpose = purpose;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.flat = flat;
        this.resident = resident;
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

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
