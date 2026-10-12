package com.test.ServiceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.Dto.BookingDto;
import com.test.Dto.BookingRequestDto;
import com.test.Dto.BookingResponseDto;
import com.test.Entities.Booking;
import com.test.Entities.Resident;
import com.test.Entities.Facility;
import com.test.Repos.BookingRepo;
import com.test.Repos.ResidentRepo;
import com.test.Repos.FacilityRepo;
import com.test.Services.BookingService;

@Service
public class BookingImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ResidentRepo residentRepo;

    @Autowired
    private FacilityRepo facilityRepo;

   
    @Override
    public BookingDto addBooking(BookingDto dto) {

        Booking booking = new Booking();

        booking.setBookingDate(dto.getBookingDate());
        booking.setTimeSlot(dto.getTimeSlot());
        booking.setStatus(dto.getStatus());

        Resident resident = residentRepo.findById(dto.getResidentId())
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        Facility facility = facilityRepo.findById(dto.getFacilid())
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        booking.setResident(resident);
        booking.setFacility(facility);

        Booking savedBooking = bookingRepo.save(booking);

        return convertToDto(savedBooking);
    }

 
    @Override
    public List<BookingDto> getAllBookings() {

        return bookingRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

  
    @Override
    public BookingDto getBookingById(int bookingid) {

        Booking booking = bookingRepo.findById(bookingid)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        return convertToDto(booking);
    }

    
    @Override
    public BookingDto updateBooking(int bookingid, BookingDto dto) {

        Booking booking = bookingRepo.findById(bookingid)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        booking.setBookingDate(dto.getBookingDate());
        booking.setTimeSlot(dto.getTimeSlot());
        booking.setStatus(dto.getStatus());

        Resident resident = residentRepo.findById(dto.getResidentId())
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        Facility facility = facilityRepo.findById(dto.getFacilid())
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        booking.setResident(resident);
        booking.setFacility(facility);

        return convertToDto(bookingRepo.save(booking));
    }

   
    @Override
    public BookingResponseDto updateBooking(int bookingid, BookingRequestDto dto) {

        Booking booking = bookingRepo.findById(bookingid)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        booking.setBookingDate(dto.getBookingDate());
        booking.setTimeSlot(dto.getTimeSlot());
        booking.setStatus(dto.getStatus());

        Resident resident = residentRepo.findById(dto.getResidentId())
                .orElseThrow(() -> new IllegalArgumentException("Resident not found"));

        Facility facility = facilityRepo.findById(dto.getFacilid())
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        booking.setResident(resident);
        booking.setFacility(facility);

        Booking updatedBooking = bookingRepo.save(booking);

        BookingResponseDto response = new BookingResponseDto();

        response.setBookingid(updatedBooking.getBookingid());
        response.setBookingDate(updatedBooking.getBookingDate());
        response.setTimeSlot(updatedBooking.getTimeSlot());
        response.setStatus(updatedBooking.getStatus());
        response.setResidentId(updatedBooking.getResident().getResidentid());
        response.setFacilid(updatedBooking.getFacility().getFacilid());

        return response;
    }

    @Override
    public void deleteBooking(int bookingid) {

        Booking booking = bookingRepo.findById(bookingid)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        bookingRepo.delete(booking);
    }

  
    @Override
    public List<BookingDto> getBookingsByResident(int residentId) {

        return bookingRepo.findByResident_Residentid(residentId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

   
    @Override
    public List<BookingDto> getBookingsByFacility(int facilid) {

        return bookingRepo.findByFacility_Facilid(facilid)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

  
    private BookingDto convertToDto(Booking booking) {

        BookingDto dto = new BookingDto();

        dto.setBookingid(booking.getBookingid());
        dto.setBookingDate(booking.getBookingDate());
        dto.setTimeSlot(booking.getTimeSlot());
        dto.setStatus(booking.getStatus());
        dto.setResidentId(booking.getResident().getResidentid());
        dto.setFacilid(booking.getFacility().getFacilid());

        return dto;
    }
}
