package com.example.hrApp.entity;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @Column(name = "job_id", length = 10)
    private String jobId;
    @Column(name = "job_title", length = 35, nullable = false)
    private String jobTitle;
    @Column(name = "min_salary", precision = 6, scale = 0)
    private Integer minSalary;
    @Column(name = "max_salary", precision = 6, scale = 0)
    private Integer maxSalary;
    /* One job → many employees */
    @OneToMany(mappedBy = "job")
    private List<Employee> employees;
    /* One job → many job history entries */
    @OneToMany(mappedBy = "job")
    private List<JobHistory> jobHistories;
    // Getters and setters
    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public Integer getMinSalary() { return minSalary; }
    public void setMinSalary(Integer minSalary) { this.minSalary = minSalary; }
    public Integer getMaxSalary() { return maxSalary; }
    public void setMaxSalary(Integer maxSalary) { this.maxSalary = maxSalary; }
    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }
    public List<JobHistory> getJobHistories() { return jobHistories; }
    public void setJobHistories(List<JobHistory> jobHistories) { this.jobHistories = jobHistories; }
}