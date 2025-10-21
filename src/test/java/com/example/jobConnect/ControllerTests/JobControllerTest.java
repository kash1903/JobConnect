package com.example.jobConnect.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.example.jobConnect.controller.JobController;
import com.example.jobConnect.model.Job;
import com.example.jobConnect.service.JobApplicationService;
import com.example.jobConnect.service.JobService;
import com.example.jobConnect.service.TwilioService;

import lombok.Data;

@Data

public class JobControllerTest {
      JobController controller;
     JobService jobService;
     JobApplicationService jobApplicationService;
     TwilioService twilioService;

    @BeforeEach
    void setUp() {
        // ✅ Create dummy services (simple replacements)
        jobService = new JobService() {
            private List<Job> jobList = new ArrayList<>();

            @Override
            public Job postJob(Job job) {
                job.setId((long) (jobList.size() + 1));
                jobList.add(job);
                return job;
            }

            @Override
            public List<Job> getAllJobs() {
                return jobList;
            }

            @Override
            public List<Job> getJobsByEmployer(Long employerId) {
                return jobList;
            }

            @Override
            public Job getJobById(Long jobId) {
                for (Job j : jobList) {
                    if (j.getId().equals(jobId)) return j;
                }
                return null;
            }

            @Override
            public void deleteJob(Long jobId) {
                jobList.removeIf(j -> j.getId().equals(jobId));
            }

            @Override
            public List<Job> searchJobsByKeyword(String keyword) {
                return jobList;
            }

            @Override
            public List<Job> searchJobsByLocation(String location) {
                return jobList;
            }
        };

        jobApplicationService = new JobApplicationService() {
            @Override
            public List<String> getAllApplicantNumbers() {
                return List.of("+911234567890", "+919876543210");
            }
        };

        twilioService = new TwilioService() {
            @Override
            public void sendSms(String number, String message) {
                System.out.println("SMS sent to " + number + " : " + message);
            }
        };

        // ✅ Create the controller and set dependencies
        // controller = new JobController();
        // controller.jobService = jobService;
        // controller.jobApplicationService = jobApplicationService;
        // controller.twilioService = twilioService;
        
    }

    // ✅ Test posting a job
    @Test
    void testPostJob() {
        Job job = new Job();
        job.setTitle("Java Developer");
        job.setLocation("Chennai");
        job.setSalary(50000);
        // job.setDeadline(new Date());

        Job savedJob = controller.postJob(job);

        assertNotNull(savedJob);
        assertEquals("Java Developer", savedJob.getTitle());
        assertEquals(1L, savedJob.getId());
    }

    // ✅ Test getAllJobs()
    @Test
    void testGetAllJobs() {
        List<Job> list = controller.getAllJobs();
        assertNotNull(list);
    }

    // ✅ Test editJob()
    @Test
    void testEditJob() {
        Job job = new Job();
        job.setTitle("Old Title");
        jobService.postJob(job);

        Job updatedJob = new Job();
        updatedJob.setTitle("New Title");
        updatedJob.setLocation("Bangalore");

        Job result = controller.editJob(1L, updatedJob);

        assertEquals("New Title", result.getTitle());
        assertEquals("Bangalore", result.getLocation());
    }

    // ✅ Test deleteJob()
    @Test
    void testDeleteJob() {
        Job job = new Job();
        job.setTitle("To Delete");
        jobService.postJob(job);

        String msg = controller.deleteJob(1L);
        assertEquals("Job deleted successfully!", msg);
    }

    // ✅ Test searchJobs()
    @Test
    void testSearchJobs() {
        List<Job> result1 = controller.searchJobs("Java", null);
        List<Job> result2 = controller.searchJobs(null, "Chennai");
        List<Job> result3 = controller.searchJobs(null, null);

        assertNotNull(result1);
        assertNotNull(result2);
        assertNotNull(result3);
    }
}
