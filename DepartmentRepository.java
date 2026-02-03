package com.example.hrApp.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrApp.entity.Department;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
public interface DepartmentRepository extends JpaRepository<Department, Long> {
 
    /* -------------------------------------------------
     * POST /api/v1/department
     * PUT  /api/v1/department
     * Add / Modify Department
     * ------------------------------------------------- */
    // save(Department department) → inherited from JpaRepository
 
 
    /* ----------------------------------------------------
     * GET /api/v1/department/findmaxsalary/{department_id}
     * Find max salary in a department
     * ------------------------------------------------- */
    @Query("""
        SELECT d.departmentName, MAX(e.salary)
        FROM Employee e
        JOIN e.department d
        WHERE d.departmentId = :deptId
        GROUP BY d.departmentName
    """)
    Object findMaxSalaryByDepartmentId(@Param("deptId") Long departmentId);
 
 
    /* -------------------------------------------------
     * GET /api/v1/department/findminsalary/{department_id}
     * Find min salary in a department
     * ------------------------------------------------- */
    @Query("""
        SELECT d.departmentName, MIN(e.salary)
        FROM Employee e
        JOIN e.department d
        WHERE d.departmentId = :deptId
        GROUP BY d.departmentName
    """)
    Object findMinSalaryByDepartmentId(@Param("deptId") Long departmentId);
 
 
    /* -------------------------------------------------
     * GET /api/v1/departments/{empid}
     * Get all departments where employee worked
     * ------------------------------------------------- */
    @Query("""
        SELECT DISTINCT d
        FROM JobHistory j
        JOIN j.department d
        WHERE j.employee.employeeId = :empId
    """)
    List<Department> findAllDepartmentsByEmployeeId(@Param("empId") Long empId);
}