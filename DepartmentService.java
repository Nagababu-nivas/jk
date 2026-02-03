package com.example.hrApp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.example.hrApp.entity.Department;
import com.example.hrApp.exception.DepartmentNotFoundException;
import com.example.hrApp.Repo.DepartmentRepository;
 
@Service
public class DepartmentService {
 
    @Autowired
    private DepartmentRepository departmentRepository;
 
    // Add new Department
    public String addDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("Department data cannot be null");
        }
        departmentRepository.save(department);
        return "Department Record Created Successfully";
    }
 
    // Update existing Department
    public String updateDepartment(Department department) throws DepartmentNotFoundException {
        if (department == null || !departmentRepository.existsById(department.getDepartmentId().longValue())) {
            throw new DepartmentNotFoundException("Department not found with id: " 
                                                  + (department != null ? department.getDepartmentId() : null));
        }
        departmentRepository.save(department);
        return "Department Record Modified Successfully";
    }
 
    // Find max salary in a department
    public Object findMaxSalaryByDepartmentId(Long deptId) throws DepartmentNotFoundException {
        Object result = departmentRepository.findMaxSalaryByDepartmentId(deptId);
        if (result == null) {
            throw new DepartmentNotFoundException("No salary data found for department id: " + deptId);
        }
        return result;
    }
 
    // Find min salary in a department
    public Object findMinSalaryByDepartmentId(Long deptId) throws DepartmentNotFoundException {
        Object result = departmentRepository.findMinSalaryByDepartmentId(deptId);
        if (result == null) {
            throw new DepartmentNotFoundException("No salary data found for department id: " + deptId);
        }
        return result;
    }
 
    // Get all departments where employee worked
    public List<Department> findAllDepartmentsByEmployeeId(Long empId) throws DepartmentNotFoundException {
        List<Department> departments = departmentRepository.findAllDepartmentsByEmployeeId(empId);
        if (departments.isEmpty()) {
            throw new DepartmentNotFoundException("No departments found for employee id: " + empId);
        }
        return departments;
    }
 
    // Optional: Get all Departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
 
    // Optional: Get Department by ID
    public Department getDepartmentById(Long deptId) throws DepartmentNotFoundException {
        return departmentRepository.findById(deptId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + deptId));
    }
 
    // Optional: Delete Department by ID
    public String deleteDepartmentById(Long deptId) throws DepartmentNotFoundException {
        if (!departmentRepository.existsById(deptId)) {
            throw new DepartmentNotFoundException("Department not found with id: " + deptId);
        }
        departmentRepository.deleteById(deptId);
        return "Department Record Deleted Successfully";
    }
}
