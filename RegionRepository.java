package com.example.hrApp.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrApp.entity.Region;
  
public interface RegionRepository extends JpaRepository<Region, Long> {
	
}