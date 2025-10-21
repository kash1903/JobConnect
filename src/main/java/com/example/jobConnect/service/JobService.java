package com.example.jobConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jobConnect.model.Job;
import com.example.jobConnect.repository.JobRepo;

@Service
public class JobService {



     @Autowired
    private JobRepo jobRepo;

    public Job postJob(Job job) {
        return jobRepo.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    public List<Job> getJobsByEmployer(Long employerId) {
        return jobRepo.findByEmployerId(employerId);
    }


    public Job getJobById(Long jobId) {
    return jobRepo.findById(jobId)
            .orElseThrow(() -> new RuntimeException("Job not found with id " + jobId));
}

        public void deleteJob(Long jobId) {
            jobRepo.deleteById(jobId);
        }



        public List<Job> searchJobsByKeyword(String keyword) {
        return jobRepo.findByTitleContainingIgnoreCase(keyword);
}

      public List<Job> searchJobsByLocation(String location) {
      return jobRepo.findByLocationContainingIgnoreCase(location);
}






    
}
