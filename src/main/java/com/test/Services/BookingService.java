package com.test.Services;

import java.util.List;

import com.test.Dto.BookingDto;
import com.test.Dto.BookingRequestDto;
import com.test.Dto.BookingResponseDto;

public interface BookingService {

    BookingDto addBooking(BookingDto dto);

    List<BookingDto> getAllBookings();

    BookingDto getBookingById(int bookingid);

    BookingDto updateBooking(int bookingid, BookingDto dto);

    BookingResponseDto updateBooking(int bookingid, BookingRequestDto dto);

    void deleteBooking(int bookingid);

    List<BookingDto> getBookingsByResident(int residentId);

    List<BookingDto> getBookingsByFacility(int facilid);
}
