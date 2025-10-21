package com.example.jobConnect.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class JobApplication {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;
    private Long userId;



    private String name;          // applicant name
    private String email;         // applicant email
    private String contactNumber; // applicant contact

    private String status = "Applied"; // default Value Applied 

     private LocalDate appliedDate = LocalDate.now();

    
}
