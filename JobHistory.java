package com.example.hrApp.entity;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "job_history")
public class JobHistory {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_history_id")
    private Long id;
 
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
 
    @Column(name = "end_date")
    private LocalDate endDate;
 
    /* ================= Relationships ================= */
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
 
    public JobHistory() {
		
	}
 
	public JobHistory(Long id, LocalDate startDate, LocalDate endDate, Employee employee, Job job,
			Department department) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
		this.job = job;
		this.department = department;
	}
 
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public LocalDate getStartDate() {
		return startDate;
	}
 
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
 
	public LocalDate getEndDate() {
		return endDate;
	}
 
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
 
	public Employee getEmployee() {
		return employee;
	}
 
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
 
	public Job getJob() {
		return job;
	}
 
	public void setJob(Job job) {
		this.job = job;
	}
 
	public Department getDepartment() {
		return department;
	}
 
	public void setDepartment(Department department) {
		this.department = department;
	}
 
	@Override
	public String toString() {
		return "JobHistory [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", employee=" + employee
				+ ", job=" + job + ", department=" + department + "]";
	}
	
}