package com.test.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Entities.Flat;

public interface FlatRepo extends JpaRepository<Flat, Integer>{

}
