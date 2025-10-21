package com.example.jobConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jobConnect.model.Job;
import com.example.jobConnect.service.JobApplicationService;
import com.example.jobConnect.service.JobService;
import com.example.jobConnect.service.TwilioService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {


     @Autowired
    private JobService jobService;

    @Autowired
    private JobApplicationService jobApplicationService;


    @Autowired TwilioService twilioService;

@PostMapping("/post")
public Job postJob(@RequestBody Job job) {
    // 1️⃣ Save the job
    Job savedJob = jobService.postJob(job);

    // 2️⃣ Fetch all applicant phone numbers from JobApplication
    List<String> phoneNumbers = jobApplicationService.getAllApplicantNumbers();

    // 3️⃣ Prepare SMS message
    String message = "📢 New Job Posted: " + savedJob.getTitle() +
                     "\nLocation: " + savedJob.getLocation() +
                     "\nCheck it out on JobConnect!";

    // 4️⃣ Send SMS via Twilio only to valid numbers
    for (String number : phoneNumbers) {
        if (number == null || number.isBlank()) {
            System.out.println("Skipping empty or null number.");
            continue;
        }

        number = number.trim(); // remove leading/trailing spaces

        if (!number.startsWith("+")) {
            System.out.println("Skipping invalid number (missing +): " + number);
            continue;
        }

        try {
            twilioService.sendSms(number, message);
        } catch (Exception e) {
            System.out.println("Failed to send SMS to " + number + ": " + e.getMessage());
        }
    }

    // 5️⃣ Return saved job
    return savedJob;
}

// new code




    // GET: Get all jobs (Job seekers view all jobs)
    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    // GET: Get jobs by employer
    @GetMapping("/employer/{employerId}")
    public List<Job> getJobsByEmployer(@PathVariable Long employerId) {
        return jobService.getJobsByEmployer(employerId);
    }

  // Edit a job
@PutMapping("/edit/{jobId}")
public Job editJob(@PathVariable Long jobId, @RequestBody Job jobDetails) {
    Job existingJob = jobService.getJobById(jobId);
    existingJob.setTitle(jobDetails.getTitle());
    existingJob.setDescription(jobDetails.getDescription());
    existingJob.setLocation(jobDetails.getLocation());
    existingJob.setSalary(jobDetails.getSalary());
    existingJob.setDeadline(jobDetails.getDeadline());
    return jobService.postJob(existingJob);
}

    // Delete a job
    @DeleteMapping("/delete/{jobId}")
    public String deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return "Job deleted successfully!";
    }


  // Search a Job ( keyword & city )
    @GetMapping("/search")
public List<Job> searchJobs(@RequestParam(required = false) String keyword,
                            @RequestParam(required = false) String location) {
    if (keyword != null) return jobService.searchJobsByKeyword(keyword);
    if (location != null) return jobService.searchJobsByLocation(location);
    return jobService.getAllJobs();
}


}
