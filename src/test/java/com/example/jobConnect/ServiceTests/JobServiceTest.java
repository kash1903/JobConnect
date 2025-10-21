package com.example.jobConnect.ServiceTests;

import com.example.jobConnect.model.Job;
import com.example.jobConnect.repository.JobRepo;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class JobServiceTest {
      @Test
    public void testPostJob() {
        JobRepo jobRepo = mock(JobRepo.class);
        Job job = new Job();
        job.setTitle("Java Developer");

        when(jobRepo.save(job)).thenReturn(job);

    }

    @Test
    public void testGetAllJobs() {
        JobRepo jobRepo = mock(JobRepo.class);

        Job job1 = new Job();
        job1.setTitle("Java Developer");
        Job job2 = new Job();
        job2.setTitle("Python Developer");

        when(jobRepo.findAll()).thenReturn(List.of(job1, job2));

    
    }

    @Test
    public void testGetJobById() {
        JobRepo jobRepo = mock(JobRepo.class);
        Job job = new Job();
        job.setId(1L);
        job.setTitle("Java Developer");

        when(jobRepo.findById(1L)).thenReturn(Optional.of(job));


    }

    @Test
    public void testSearchJobsByKeyword() {
        JobRepo jobRepo = mock(JobRepo.class);
        Job job = new Job();
        job.setTitle("Java Developer");

        when(jobRepo.findByTitleContainingIgnoreCase("Java")).thenReturn(List.of(job));

 
    }
}
