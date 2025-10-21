package com.example.jobConnect.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.example.jobConnect.model.JobApplication;
import com.example.jobConnect.repository.JobApplicationRepo;
import com.example.jobConnect.service.JobApplicationService;

public class JobApplicationServiceTest {
      @Test
    public void testApplyForJob() {
        // Mock the repository
        JobApplicationRepo jobApplicationRepo = mock(JobApplicationRepo.class);

        JobApplication app = new JobApplication();
        app.setContactNumber("+911234567890");
        app.setStatus("Applied");

        // When save() is called, return the same app
        when(jobApplicationRepo.save(app)).thenReturn(app);

        JobApplicationService service = new JobApplicationService();
        // service.jobApplicationRepo = jobApplicationRepo;

        JobApplication result = service.applyForJob(app);
        assertEquals("Applied", result.getStatus());
        assertEquals("+911234567890", result.getContactNumber());
    }

    @Test
    public void testGetAllApplicantNumbers() {
        JobApplicationRepo jobApplicationRepo = mock(JobApplicationRepo.class);

        JobApplication a1 = new JobApplication();
        a1.setContactNumber("+911234567890");

        JobApplication a2 = new JobApplication();
        a2.setContactNumber("");

        when(jobApplicationRepo.findAll()).thenReturn(List.of(a1, a2));

        JobApplicationService service = new JobApplicationService();
        // service.jobApplicationRepo = jobApplicationRepo;

        List<String> numbers = service.getAllApplicantNumbers();
        assertEquals(1, numbers.size());
        assertEquals("+911234567890", numbers.get(0));
    }
}
