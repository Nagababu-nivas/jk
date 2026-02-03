package com.example.hrApp.service;

import java.time.LocalDate;
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import com.example.hrApp.entity.JobHistory;
import com.example.hrApp.exception.JobHistoryNotFoundException;
import com.example.hrApp.Repo.JobHistoryRepository;
 
@Service
public class JobHistoryService {
 
    @Autowired
    private JobHistoryRepository jobHistoryRepository;
 
    // Add new JobHistory entry
    public String addJobHistory(JobHistory jobHistory) {
        if (jobHistory == null) {
            throw new IllegalArgumentException("JobHistory data cannot be null");
        }
        jobHistoryRepository.save(jobHistory);
        return "JobHistory Record Created Successfully";
    }
 
    // Update end date for an employee's job history
    @Transactional
    public String updateEndDate(Long empId, LocalDate endDate) {
        List<JobHistory> histories = jobHistoryRepository.findAll().stream()
                .filter(jh -> jh.getEmployee().getEmployeeId().equals(empId) && jh.getEndDate() == null)
                .toList();
 
        if (histories.isEmpty()) {
            throw new JobHistoryNotFoundException("No active JobHistory found for employee id: " + empId);
        }
 
        // Update using repository query
        jobHistoryRepository.updateEndDateByEmployeeId(empId, endDate);
        return "JobHistory End Date Updated Successfully";
    }
 
    // Calculate total experience in days (or object from DB)
    public Object getTotalExperience(Long empId) {
        Object totalExp = jobHistoryRepository.findTotalExperienceByEmployeeId(empId);
        if (totalExp == null) {
            throw new JobHistoryNotFoundException("No JobHistory found for employee id: " + empId);
        }
        return totalExp;
    }
 
    // Get employees with less than one year experience
    public List<JobHistory> getEmployeesWithLessThanOneYearExperience(Long empId) {
        List<JobHistory> result = jobHistoryRepository.findEmployeesWithLessThanOneYearExperience(empId);
        if (result.isEmpty()) {
            throw new JobHistoryNotFoundException("No JobHistory entries with less than 1 year experience for employee id: " + empId);
        }
        return result;
    }
 
    // Optional: Get all JobHistory entries
    public List<JobHistory> getAllJobHistories() {
        return jobHistoryRepository.findAll();
    }
}