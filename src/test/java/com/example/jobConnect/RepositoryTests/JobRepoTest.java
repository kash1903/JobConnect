package com.example.jobConnect.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.jobConnect.model.Job;
import com.example.jobConnect.repository.JobRepo;
import com.example.jobConnect.service.JobService;

public class JobRepoTest {
       @Test
    public void testPostJob() {
        JobRepo repo = mock(JobRepo.class);
        Job job = new Job();
        job.setTitle("Java Developer");

        when(repo.save(job)).thenReturn(job);

        JobService service = new JobService();
        // service.jobRepo = repo;

        Job saved = service.postJob(job);
        assertEquals("Java Developer", saved.getTitle());
    }

    @Test
    public void testGetAllJobs() {
        JobRepo repo = mock(JobRepo.class);
        Job job1 = new Job();
        Job job2 = new Job();

        when(repo.findAll()).thenReturn(Arrays.asList(job1, job2));

        JobService service = new JobService();
        // service.jobRepo = repo;

        List<Job> jobs = service.getAllJobs();
        assertEquals(2, jobs.size());
    }

    @Test
    public void testGetJobsByEmployer() {
        JobRepo repo = mock(JobRepo.class);
        Job job = new Job();
        when(repo.findByEmployerId(1L)).thenReturn(Arrays.asList(job));

        JobService service = new JobService();
        // service.jobRepo = repo;

        List<Job> jobs = service.getJobsByEmployer(1L);
        assertEquals(1, jobs.size());
    }

    @Test
    public void testGetJobById() {
        JobRepo repo = mock(JobRepo.class);
        Job job = new Job();
        job.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(job));

        JobService service = new JobService();
        // service.jobRepo = repo;

        Job found = service.getJobById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    public void testDeleteJob() {
        JobRepo repo = mock(JobRepo.class);
        JobService service = new JobService();
        // service.jobRepo = repo;

        service.deleteJob(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}
