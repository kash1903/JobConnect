package com.example.jobConnect.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.example.jobConnect.model.JobApplication;
import com.example.jobConnect.repository.JobApplicationRepo;
import com.example.jobConnect.service.JobApplicationService;

public class JobApplicationRepoTest {
    

     @Test
    public void testApplyForJob() {
        JobApplicationRepo repo = mock(JobApplicationRepo.class);
        JobApplication app = new JobApplication();
        app.setUserId(1L);

        when(repo.save(app)).thenReturn(app);

        JobApplicationService service = new JobApplicationService();
        // service.jobApplicationRepo = repo;

        JobApplication result = service.applyForJob(app);
        assertEquals("Applied", result.getStatus());
    }

    @Test
    public void testGetApplicantsByJob() {
        JobApplicationRepo repo = mock(JobApplicationRepo.class);
        JobApplication app1 = new JobApplication();
        JobApplication app2 = new JobApplication();

        when(repo.findByJobId(1L)).thenReturn(Arrays.asList(app1, app2));

        JobApplicationService service = new JobApplicationService();
        // service.jobApplicationRepo = repo;

        List<JobApplication> result = service.getApplicantsByJob(1L);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAllApplicantNumbers() {
        JobApplicationRepo repo = mock(JobApplicationRepo.class);

        when(repo.findAllApplicantNumbers()).thenReturn(Arrays.asList("+911234567890", "+919876543210"));

        JobApplicationService service = new JobApplicationService();
        // service.jobApplicationRepo = repo;

        List<String> numbers = service.getAllApplicantNumbers();
        assertEquals(2, numbers.size());
        assertTrue(numbers.contains("+911234567890"));
    }

    @Test
    public void testUpdateApplicationStatus() {
        JobApplicationRepo repo = mock(JobApplicationRepo.class);
        JobApplication app = new JobApplication();
        app.setId(1L);
        app.setStatus("Applied");

        when(repo.findById(1L)).thenReturn(Optional.of(app));
        when(repo.save(app)).thenReturn(app);

        JobApplicationService service = new JobApplicationService();
        // service.jobApplicationRepo = repo;

        JobApplication updated = service.updateApplicationStatus(1L, "Shortlisted");
        assertEquals("Shortlisted", updated.getStatus());
    }
}
