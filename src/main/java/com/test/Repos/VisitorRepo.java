package com.test.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.Entities.Visitor;

@Repository
public interface VisitorRepo extends JpaRepository<Visitor, Integer> {

    List<Visitor> findByResident_Residentid(int residentId);

    List<Visitor> findByFlat_Flatid(int flatId);
}
