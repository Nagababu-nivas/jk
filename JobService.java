package com.example.hrApp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.hrApp.entity.Job;
import com.example.hrApp.exception.JobNotFoundException;
import com.example.hrApp.Repo.JobRepository;
 
@Service
public class JobService {
 
    @Autowired
    private JobRepository jobRepository;
 
    // Add new Job
    public String addJob(Job job) {
        if (job == null) throw new IllegalArgumentException("Job data cannot be null");
        jobRepository.save(job);
        return "Record Created Successfully";
    }
 
    // Update existing Job
    public String updateJob(Job job) throws JobNotFoundException {
        if (job == null || !jobRepository.existsById(job.getJobId())) {
            throw new JobNotFoundException("Job not found with id: " + (job != null ? job.getJobId() : null));
        }
        jobRepository.save(job);
        return "Record Modified Successfully";
    }
 
    // Get all Jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
 
    // Update min and max salary
    @Transactional
    public Job updateMinAndMaxSalary(String jobId, Integer minSalary, Integer maxSalary) throws JobNotFoundException {
        if (!jobRepository.existsById(jobId)) {
            throw new JobNotFoundException("Job not found with id: " + jobId);
        }
 
        jobRepository.updateMinAndMaxSalary(jobId, minSalary, maxSalary);
 
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found after salary update: " + jobId));
    }
 
    // Get Job by ID
    public Job getJobById(String jobId) throws JobNotFoundException {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found with id: " + jobId));
    }
 
    // Delete Job by ID
    public String deleteJobById(String jobId) throws JobNotFoundException {
        if (!jobRepository.existsById(jobId)) {
            throw new JobNotFoundException("Job not found with id: " + jobId);
        }
        jobRepository.deleteById(jobId);
        return "Record deleted Successfully";
    }
}