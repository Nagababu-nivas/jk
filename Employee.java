package com.example.hrApp.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
 
@Entity
@Table(name = "employees")
public class Employee {
 
    @Id
    @Column(name = "employee_id")
    private Long employeeId;
 
    @Column(name = "first_name", length = 20)
    private String firstName;
 
    @Column(name = "last_name", length = 25, nullable = false)
    private String lastName;
 
    @Column(name = "email", length = 25, nullable = false, unique = true)
    private String email;
 
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
 
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
 
    @Column(name = "salary", precision = 8, scale = 2)
    private BigDecimal salary;
    
    @Column(name = "commission_pct", precision = 2, scale = 2)
    private BigDecimal commissionPct;
    

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
 
    @OneToMany(mappedBy = "manager")
    private List<Employee> subordinates;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
 
    @OneToMany(mappedBy = "manager")
    private List<Department> managedDepartments;
    
    
    // Getters and setters

    public Long getEmployeeId() { 
    	return employeeId; 
	}

    public void setEmployeeId(Long employeeId) { 
    	this.employeeId = employeeId; 
	}
 
    public String getFirstName() { 
    	return firstName; 
	}

    public void setFirstName(String firstName) { this.firstName = firstName; }
 
    public String getLastName() { 
    	return lastName; 
	}

    public void setLastName(String lastName) { 
    	this.lastName = lastName; 
	}
 
    public String getEmail() { 
    	return email; 
	}

    public void setEmail(String email) { 
    	this.email = email; 
	}
 
    public String getPhoneNumber() { 
    	return phoneNumber; 
	}

    public void setPhoneNumber(String phoneNumber) { 
    	this.phoneNumber = phoneNumber; 
	}
 
    public LocalDate getHireDate() { 
    	return hireDate; 
	}

    public void setHireDate(LocalDate hireDate) { 
    	this.hireDate = hireDate; 
	}
 
    public Job getJob() { 
    	return job; 
	}

    public void setJob(Job job) { 
    	this.job = job; 
	}
 
    public BigDecimal getSalary() { 
    	return salary; 
	}

    public void setSalary(BigDecimal salary) { 
    	this.salary = salary; 
	}
 
    public BigDecimal getCommissionPct() { 
    	return commissionPct; 
	}

    public void setCommissionPct(BigDecimal commissionPct) { 
    	this.commissionPct = commissionPct; 
	}
 
    public Employee getManager() { 
    	return manager; 
	}

    public void setManager(Employee manager) { 
    	this.manager = manager; 
	}
 
    public List<Employee> getSubordinates() { 
    	return subordinates; 
	}

    public void setSubordinates(List<Employee> subordinates) { 
    	this.subordinates = subordinates; 
	}
 
    public Department getDepartment() { 
    	return department; 
	}

    public void setDepartment(Department department) { 
    	this.department = department; 
	}
 
    public List<Department> getManagedDepartments() { 
    	return managedDepartments; 
	}

    public void setManagedDepartments(List<Department> managedDepartments) { 
    	this.managedDepartments = managedDepartments; 
	}

}
