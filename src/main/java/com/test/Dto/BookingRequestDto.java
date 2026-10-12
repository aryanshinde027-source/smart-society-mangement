package com.test.Dto;

import java.time.LocalDate;

public class BookingRequestDto {

    private LocalDate bookingDate;
    private String timeSlot;
    private String status;
    private int residentId;
    private int facilid;

    public BookingRequestDto() {
        super();
    }

    public BookingRequestDto(LocalDate bookingDate, String timeSlot,
            String status, int residentId, int facilid) {
        super();
        this.bookingDate = bookingDate;
        this.timeSlot = timeSlot;
        this.status = status;
        this.residentId = residentId;
        this.facilid = facilid;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResidentId() {
        return residentId;
    }

    public void setResidentId(int residentId) {
        this.residentId = residentId;
    }

    public int getFacilid() {
        return facilid;
    }

    public void setFacilid(int facilid) {
        this.facilid = facilid;
    }
}
