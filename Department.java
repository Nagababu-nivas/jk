package com.example.hrApp.entity;

import jakarta.persistence.*;
import java.util.List;
 
@Entity
@Table(name = "departments")
public class Department {
 
    @Id
    @Column(name = "department_id", nullable = false)
    private Long departmentId;
 
    @Column(name = "department_name", length = 30, nullable = false)
    private String departmentName;
 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;
 
    
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;
 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;
 
    
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<JobHistory> jobHistories;
 
    
    public Department() {
    }
 
    public Department(Long departmentId, String departmentName, Location location, Employee manager) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.location = location;
        this.manager = manager;
    }
 
    // Getters and Setters
    public Long getDepartmentId() {
        return departmentId;
    }
 
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
 
    public String getDepartmentName() {
        return departmentName;
    }
 
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
 
    public Location getLocation() {
        return location;
    }
 
    public void setLocation(Location location) {
        this.location = location;
    }
 
    public List<Employee> getEmployees() {
        return employees;
    }
 
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
 
    public Employee getManager() {
        return manager;
    }
 
    public void setManager(Employee manager) {
        this.manager = manager;
    }
 
    public List<JobHistory> getJobHistories() {
        return jobHistories;
    }
 
    public void setJobHistories(List<JobHistory> jobHistories) {
        this.jobHistories = jobHistories;
    }
}