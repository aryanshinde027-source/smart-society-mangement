package com.test.Repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.test.Entities.Facility;

public interface FacilityRepo extends JpaRepository<Facility, Integer> {

    List<Facility> findBySocietyId_Id(int societyId);
}