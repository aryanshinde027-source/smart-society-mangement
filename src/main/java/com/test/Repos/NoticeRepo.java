package com.test.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Entities.Notice;

public interface NoticeRepo extends JpaRepository<Notice, Integer>{

}
