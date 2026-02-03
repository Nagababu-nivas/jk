package com.example.hrApp.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hrApp.entity.Country;
 
public interface CountryRepository extends JpaRepository<Country, String> {
	
}