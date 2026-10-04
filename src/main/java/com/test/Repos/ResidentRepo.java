package com.test.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Entities.Resident;

public interface ResidentRepo extends JpaRepository<Resident, Integer> {

}
