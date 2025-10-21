package com.example.jobConnect.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobConnect.model.Job;
// import com.example.jobConnect.model.Job;
import com.example.jobConnect.model.JobApplication;
import com.example.jobConnect.repository.JobApplicationRepo;
import com.example.jobConnect.repository.JobRepo;

@Service
public class JobApplicationService {
     @Autowired
    private JobApplicationRepo jobApplicationRepo;


    @Autowired 
    private JobRepo jobRepo;

    public JobApplication applyForJob(JobApplication application) {
        application.setStatus("Applied");
        return jobApplicationRepo.save(application);
    }

    public List<JobApplication> getApplicantsByJob(Long jobId) {
        return jobApplicationRepo.findByJobId(jobId);
    }

    public List<JobApplication> getApplicationsByUser(Long userId) {
        return jobApplicationRepo.findByUserId(userId);
    }


      // 🆕 New method to update status
    public JobApplication updateApplicationStatus(Long id, String status) {
        Optional<JobApplication> optionalApp = jobApplicationRepo.findById(id);
        if (optionalApp.isPresent()) {
            JobApplication application = optionalApp.get();
            application.setStatus(status);
            return jobApplicationRepo.save(application);
        } else {
            throw new RuntimeException("Application not found with id: " + id);
        }
    }

    
    // 🆕 New method to show all applications with job details for a user
    public List<String> getUserApplicationStatuses(Long userId) {
        List<JobApplication> applications = jobApplicationRepo.findByUserId(userId);
        List<String> result = new ArrayList<>();

        for (JobApplication app : applications) {
            Optional<Job> job = jobRepo.findById(app.getJobId());
            String jobTitle = job.isPresent() ? job.get().getTitle() : "Unknown Job";
            String info = "Job Title: " + jobTitle + " | Status: " + app.getStatus();
            result.add(info);
        }

        return result;
    }



    // 🆕 New method to get all applicant phone numbers
    public List<String> getAllApplicantNumbers() {
    List<JobApplication> applications = jobApplicationRepo.findAll();
    List<String> numbers = new ArrayList<>();
    for (JobApplication app : applications) {
        if (app.getContactNumber() != null && !app.getContactNumber().isEmpty()) {
            numbers.add(app.getContactNumber());
        }
    }
    return numbers;
}





    
    
}
