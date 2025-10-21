package com.example.jobConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobConnect.model.JobApplication;
import com.example.jobConnect.service.JobApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class JobApplicationController {

      @Autowired
    public JobApplicationService jobApplicationService;

    // Apply for a job
    @PostMapping("/apply")
    public JobApplication applyForJob(@RequestBody JobApplication application) {
        return jobApplicationService.applyForJob(application);
    }

    // Get all applicants for a job
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getApplicants(@PathVariable Long jobId) {
        return jobApplicationService.getApplicantsByJob(jobId);
    }

    // Get all jobs a user applied to
    @GetMapping("/user/{userId}")
    public List<JobApplication> getUserApplications(@PathVariable Long userId) {
        return jobApplicationService.getApplicationsByUser(userId);
    }




     // 🆕 Update status of a job application (Employer use)
    @PutMapping("/{id}/status")
    public JobApplication updateStatus(@PathVariable Long id, @RequestParam String status) {
        return jobApplicationService.updateApplicationStatus(id, status);
    }
     

    // Get all job statuses for a user
@GetMapping("/user/{userId}/status")
public List<String> getUserApplicationStatuses(@PathVariable Long userId) {
    return jobApplicationService.getUserApplicationStatuses(userId);
}

}








       



    

