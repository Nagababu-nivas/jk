package com.example.hrApp.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrApp.entity.Location;
  
public interface LocationRepository extends JpaRepository<Location, Long> {
	
}