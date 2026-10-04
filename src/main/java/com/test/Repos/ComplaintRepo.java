package com.test.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Entities.Complaint;

public interface ComplaintRepo extends JpaRepository<Complaint, Integer> {

}
