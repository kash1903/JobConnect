package com.example.jobConnect.repository;

import com.example.jobConnect.model.Job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository <Job,Long> {


    List<Job> findByEmployerId(Long employerId);
    List<Job> findByTitleContainingIgnoreCase(String keyword);
    List<Job> findByLocationContainingIgnoreCase(String location);

    
}
