package com.example.hrApp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.example.hrApp.entity.Country;
import com.example.hrApp.exception.CountryNotFoundException;
import com.example.hrApp.Repo.CountryRepository;
 
@Service
public class CountryService {
 
    @Autowired
    private CountryRepository countryRepository;
 
    // Add new country
    public String addCountry(Country country) {
        if (country == null) {
            throw new IllegalArgumentException("Country data cannot be null");
        }
        countryRepository.save(country);
        return "Record Created Successfully";
    }
 
    // Update existing country
    public String updateCountry(Country country) {
        if (country == null || !countryRepository.existsById(country.getCountryId())) {
            throw new CountryNotFoundException("Country not found with id: " + country.getCountryId());
        }
        countryRepository.save(country);
        return "Record Modified Successfully";
    }
 
    // Get all countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
 
    // Get country by ID
    public Country getCountryById(String id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country not found with id: " + id));
    }
 
    // Delete country by ID
    public String deleteCountryById(String id) {
        if (!countryRepository.existsById(id)) {
            throw new CountryNotFoundException("Country not found with id: " + id);
        }
        countryRepository.deleteById(id);
        return "Country Deleted Successfully";
    }
}