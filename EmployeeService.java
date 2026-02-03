package com.example.hrApp.service;
import java.time.LocalDate;
import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.example.hrApp.entity.Employee;
import com.example.hrApp.entity.Job;
import com.example.hrApp.exception.EmployeeNotFoundException;
import com.example.hrApp.Repo.EmployeeRepository;
 
@Service
public class EmployeeService {
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    // Add new Employee
    public String addEmployee(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("Employee data cannot be null");
        employeeRepository.save(employee);
        return "Employee Record Created Successfully";
    }
 
    // Update existing Employee
    public String updateEmployee(Employee employee) {
        if (employee == null || !employeeRepository.existsById(employee.getEmployeeId())) {
            throw new EmployeeNotFoundException("Employee not found with id: "
                                                + (employee != null ? employee.getEmployeeId() : null));
        }
        employeeRepository.save(employee);
        return "Employee Record Modified Successfully";
    }
 
    // Basic searches
    public Employee findByFirstName(String firstName) {
        Employee emp = employeeRepository.findByFirstName(firstName);
        if (emp == null) throw new EmployeeNotFoundException("Employee not found with firstName: " + firstName);
        return emp;
    }
 
    public Employee findByEmail(String email) {
        Employee emp = employeeRepository.findByEmail(email);
        if (emp == null) throw new EmployeeNotFoundException("Employee not found with email: " + email);
        return emp;
    }
 
    public Employee findByPhoneNumber(String phoneNumber) {
        Employee emp = employeeRepository.findByPhoneNumber(phoneNumber);
        if (emp == null) throw new EmployeeNotFoundException("Employee not found with phoneNumber: " + phoneNumber);
        return emp;
    }
 
    // Commission related
    public List<Employee> findAllEmployeeWithNoCommission() {
        List<Employee> employees = employeeRepository.findAllEmployeeWithNoCommission();
        if (employees.isEmpty()) throw new EmployeeNotFoundException("No employees found without commission");
        return employees;
    }
 
    public Double findTotalCommissionByDepartment(Long deptId) {
        Double totalCommission = employeeRepository.findTotalCommissionIssuedToEmployeeByDepartment(deptId);
        if (totalCommission == null) return 0.0;
        return totalCommission;
    }
 
    // Department related
    public List<Employee> findByDepartment(Long deptId) {
        List<Employee> employees = employeeRepository.findByDepartment_DepartmentId(deptId);
        if (employees.isEmpty()) throw new EmployeeNotFoundException("No employees found in department id: " + deptId);
        return employees;
    }
 
    public List<Object[]> countAllEmployeesGroupByDepartment() {
        List<Object[]> result = employeeRepository.countAllEmployeesGroupByDepartment();
        if (result.isEmpty()) throw new EmployeeNotFoundException("No employee data found grouped by department");
        return result;
    }
 
    // Manager related
    public List<Employee> listAllManagers() {
        List<Employee> managers = employeeRepository.listAllManagerDetails();
        if (managers.isEmpty()) throw new EmployeeNotFoundException("No managers found");
        return managers;
    }
 
    // Location related
    public List<Object[]> countAllEmployeesGroupByLocation() {
        List<Object[]> result = employeeRepository.countAllEmployeesGroupByLocation();
        if (result.isEmpty()) throw new EmployeeNotFoundException("No employee data found grouped by location");
        return result;
    }
 
    // Job & salary related
    public Object findMaxSalaryOfJobByEmployeeId(Long empId) {
        Object result = employeeRepository.findMaxSalaryOfJobByEmployeeId(empId);
        if (result == null) throw new EmployeeNotFoundException("No salary data found for employee id: " + empId);
        return result;
    }
 
    // Open positions
    public List<Job> findAllOpenPositions() {
        List<Job> jobs = employeeRepository.findAllOpenPositions();
        if (jobs.isEmpty()) throw new EmployeeNotFoundException("No open positions found");
        return jobs;
    }
 
    public List<Job> findAllOpenPositionsByDepartment(Long deptId) {
        List<Job> jobs = employeeRepository.findAllOpenPositionsByDepartment(deptId);
        if (jobs.isEmpty()) throw new EmployeeNotFoundException("No open positions found in department id: " + deptId);
        return jobs;
    }
 
    // Hire date queries
    public List<Employee> listAllEmployeeByHireDate(LocalDate fromDate, LocalDate toDate) {
        List<Employee> employees = employeeRepository.listAllEmployeeByHireDate(fromDate, toDate);
        if (employees.isEmpty()) throw new EmployeeNotFoundException(
                "No employees found hired between " + fromDate + " and " + toDate);
        return employees;
    }
 
    // Optional: Get Employee by ID
    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + empId));
    }
 
    // Optional: Delete Employee by ID
    public String deleteEmployeeById(Long empId) {
        if (!employeeRepository.existsById(empId)) {
            throw new EmployeeNotFoundException("Employee not found with id: " + empId);
        }
        employeeRepository.deleteById(empId);
        return "Employee Record Deleted Successfully";
    }
 
    // Optional: Get all Employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}