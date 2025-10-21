package com.example.jobConnect.ControllerTests;

import com.example.jobConnect.controller.JobApplicationController;
import com.example.jobConnect.model.JobApplication;
import com.example.jobConnect.service.JobApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JobApplicationControllerTest {
 

    @MockBean
    private JobApplicationService jobApplicationService;
    private JobApplicationController controller;
    private JobApplicationService service;

    @BeforeEach
    void setUp() {
        // Create a dummy service manually (not using @Mock yet)
        service = new JobApplicationService() {
            private List<JobApplication> applications = new ArrayList<>();

            @Override
            public JobApplication applyForJob(JobApplication app) {
                applications.add(app);
                return app;
            }

            @Override
            public List<JobApplication> getApplicantsByJob(Long jobId) {
                return applications;
            }

            @Override
            public List<JobApplication> getApplicationsByUser(Long userId) {
                return applications;
            }

            @Override
            public JobApplication updateApplicationStatus(Long id, String status) {
                JobApplication app = new JobApplication();
                app.setId(id);
                app.setStatus(status);
                return app;
            }

            @Override
            public List<String> getUserApplicationStatuses(Long userId) {
                return List.of("Applied", "Shortlisted");
            }
        };

        controller = new JobApplicationController();
        controller.jobApplicationService = service;
    }

    // ✅ Test applyForJob()
    @Test
    void testApplyForJob() {
        JobApplication app = new JobApplication();
        app.setJobId(1L);
        app.setUserId(2L);
        app.setStatus("Applied");

        JobApplication saved = controller.applyForJob(app);

        assertEquals("Applied", saved.getStatus());
    }

    // ✅ Test getApplicants()
    @Test
    void testGetApplicants() {
        List<JobApplication> list = controller.getApplicants(1L);
        assertNotNull(list);
    }

    // ✅ Test updateStatus()
    @Test
    void testUpdateStatus() {
        JobApplication updated = controller.updateStatus(1L, "Shortlisted");
        assertEquals("Shortlisted", updated.getStatus());
    }

    // ✅ Test getUserApplicationStatuses()
    @Test
    void testGetUserApplicationStatuses() {
        List<String> statuses = controller.getUserApplicationStatuses(1L);
        assertTrue(statuses.contains("Applied"));
    }
}
