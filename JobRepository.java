package com.example.hrApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.hrApp.entity.Job;
import jakarta.transaction.Transactional;
public interface JobRepository extends JpaRepository<Job, String> { // <-- ID type is String
    @Modifying
    @Transactional
    @Query("UPDATE Job j SET j.minSalary = :minSalary, j.maxSalary = :maxSalary WHERE j.jobId = :jobId")
    void updateMinAndMaxSalary(@Param("jobId") String jobId,
                               @Param("minSalary") Integer minSalary,
                               @Param("maxSalary") Integer maxSalary);
}