package com.example.jobConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jobConnect.model.JobApplication;

@Repository

public interface JobApplicationRepo extends JpaRepository<JobApplication,Long> {
    
      List<JobApplication> findByJobId(Long jobId);
    List<JobApplication> findByUserId(Long userId);


    // New method to get all unique applicant phone numbers
    @Query("SELECT DISTINCT ja.contactNumber FROM JobApplication ja")
    List<String> findAllApplicantNumbers();



}
