package com.example.hrApp.Repo; 
import java.time.LocalDate;
import java.util.List; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; 
import com.example.hrApp.entity.Employee;
import com.example.hrApp.entity.Job;
 
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
 
    /* ---------- BASIC SEARCH ---------- */
 
    Employee findByFirstName(String firstName);
 
    Employee findByEmail(String email);
 
    Employee findByPhoneNumber(String phoneNumber);
 
 
    /* ---------- COMMISSION RELATED ---------- */
 
    @Query("SELECT e FROM Employee e WHERE e.commissionPct IS NULL")
    List<Employee> findAllEmployeeWithNoCommission();
 
    @Query("SELECT SUM(e.commissionPct) FROM Employee e WHERE e.department.departmentId = :deptId")
    Double findTotalCommissionIssuedToEmployeeByDepartment(@Param("deptId") Long departmentId);
 
 
    /* ---------- DEPARTMENT RELATED ---------- */
 
    List<Employee> findByDepartment_DepartmentId(Long departmentId);
 
    @Query("SELECT e.department.departmentName, COUNT(e) FROM Employee e GROUP BY e.department.departmentName")
    List<Object[]> countAllEmployeesGroupByDepartment();
 
 
    /* ---------- MANAGER RELATED ---------- */
 
    @Query("SELECT e FROM Employee e WHERE e.employeeId IN (SELECT DISTINCT emp.manager.employeeId FROM Employee emp WHERE emp.manager IS NOT NULL)")
    List<Employee> listAllManagerDetails();
 
 
    /* ---------- LOCATION RELATED ---------- */
 
    @Query("SELECT e.department.location.city, COUNT(e) FROM Employee e GROUP BY e.department.location.city")
    List<Object[]> countAllEmployeesGroupByLocation();
 
 
    /* ---------- JOB & SALARY ---------- */
 
    @Query("SELECT e.job.jobTitle, MAX(e.salary) FROM Employee e WHERE e.employeeId = :empId GROUP BY e.job.jobTitle")
    Object findMaxSalaryOfJobByEmployeeId(@Param("empId") Long employeeId);
 
 
    /* ---------- OPEN POSITIONS ---------- */
 
    @Query("SELECT j FROM Job j WHERE j.jobId NOT IN (SELECT DISTINCT e.job.jobId FROM Employee e)")
    List<Job> findAllOpenPositions();
 
    @Query("SELECT j FROM Job j WHERE j.department.departmentId = :deptId AND j.jobId NOT IN (SELECT DISTINCT e.job.jobId FROM Employee e)")
    List<Job> findAllOpenPositionsByDepartment(@Param("deptId") Long departmentId);
 
 
    /* ---------- HIRE DATE ---------- */
 
    @Query("SELECT e FROM Employee e WHERE e.hireDate BETWEEN :fromDate AND :toDate")
    List<Employee> listAllEmployeeByHireDate(@Param("fromDate") LocalDate fromHireDate,
                                             @Param("toDate") LocalDate toHireDate);
}