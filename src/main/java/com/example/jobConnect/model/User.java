package com.example.jobConnect.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data                   // automatically creates getters, setters, toString, etc.
@NoArgsConstructor
@AllArgsConstructor

public class User {


      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    
    private String role;   // JOB_SEEKER or EMPLOYER


    
}
