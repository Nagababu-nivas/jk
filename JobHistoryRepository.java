package com.example.hrApp.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrApp.entity.JobHistory;
import java.time.LocalDate;
import java.util.List; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
 
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
 
    /* -------------------------------------------------
     * POST /api/v1/job_history/{empid}/{startdate}/{job_id}/{department_id}
     * Add Job History entry
     * ------------------------------------------------- */
    // save(JobHistory jobHistory) → inherited from JpaRepository
 
 
    /* -------------------------------------------------
     * PUT /api/v1/job_history/{empid}/{enddate}
     * Update end date of Job History
     * ------------------------------------------------- */
    @Query("UPDATE JobHistory j SET j.endDate = :endDate WHERE j.employee.employeeId = :empId AND j.endDate IS NULL")
    void updateEndDateByEmployeeId(@Param("empId") Long empId,
                                   @Param("endDate") LocalDate endDate);
 
 
    /* -------------------------------------------------
     * GET /api/v1/job_history/totalyearsofexperience/{emp_id}
     * Calculate total experience
     * ------------------------------------------------- */
    @Query("""
        SELECT SUM(
            FUNCTION('AGE', 
                COALESCE(j.endDate, CURRENT_DATE), 
                j.startDate
            )
        )
        FROM JobHistory j
        WHERE j.employee.employeeId = :empId
    """)
    Object findTotalExperienceByEmployeeId(@Param("empId") Long empId);
 
 
    /* -------------------------------------------------
     * GET /api/v1/job_history/lessthanoneyearexperience/{emp_id}
     * List employees with < 1 year experience
     * ------------------------------------------------- */
    @Query("""
        SELECT j FROM JobHistory j
        WHERE j.employee.employeeId = :empId
        AND (FUNCTION('DATE_PART','year',
             AGE(COALESCE(j.endDate, CURRENT_DATE), j.startDate))) < 1
    """)
    List<JobHistory> findEmployeesWithLessThanOneYearExperience(@Param("empId") Long empId);
}