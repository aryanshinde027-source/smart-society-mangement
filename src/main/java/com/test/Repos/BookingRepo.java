package com.test.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.Entities.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {

    List<Booking> findByResident_Residentid(int residentId);

    List<Booking> findByFacility_Facilid(int facilid);
}