package com.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Dto.BookingDto;
import com.test.Dto.BookingRequestDto;
import com.test.Dto.BookingResponseDto;
import com.test.Services.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins  = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

 
    @PostMapping("/add")
    public BookingDto addBooking(@RequestBody BookingDto dto) {
        return bookingService.addBooking(dto);
    }

   
    @GetMapping("/all")
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    
    @GetMapping("/{bookingid}")
    public BookingDto getBookingById(@PathVariable int bookingid) {
        return bookingService.getBookingById(bookingid);
    }

    
    @PutMapping("/update/{bookingid}")
    public BookingDto updateBooking(
            @PathVariable int bookingid,
            @RequestBody BookingDto dto) {

        return bookingService.updateBooking(bookingid, dto);
    }

   
    @PutMapping("/update-request/{bookingid}")
    public BookingResponseDto updateBookingRequest(
            @PathVariable int bookingid,
            @RequestBody BookingRequestDto dto) {

        return bookingService.updateBooking(bookingid, dto);
    }

  
    @DeleteMapping("/delete/{bookingid}")
    public String deleteBooking(@PathVariable int bookingid) {
        bookingService.deleteBooking(bookingid);
        return "Booking deleted successfully";
    }

  
    @GetMapping("/resident/{residentId}")
    public List<BookingDto> getBookingsByResident(
            @PathVariable int residentId) {

        return bookingService.getBookingsByResident(residentId);
    }

    
    @GetMapping("/facility/{facilid}")
    public List<BookingDto> getBookingsByFacility(
            @PathVariable int facilid) {

        return bookingService.getBookingsByFacility(facilid);
    }
}
