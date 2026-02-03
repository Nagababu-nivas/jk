package com.example.hrApp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import com.example.hrApp.entity.Location;
import com.example.hrApp.exception.LocationNotFoundException;
import com.example.hrApp.Repo.LocationRepository;
 
@Service
public class LocationService {
 
    @Autowired
    private LocationRepository locationRepository;
 
    // Add new Location
    public String addLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location data cannot be null");
        }
        locationRepository.save(location);
        return "Record Created Successfully";
    }
 
    // Update existing Location
    public String updateLocation(Location location) {
        if (location == null || !locationRepository.existsById(location.getLocationId())) {
            throw new LocationNotFoundException("Location not found with id: " + location.getLocationId());
        }
        locationRepository.save(location);
        return "Record Modified Successfully";
    }
 
    // Get all Locations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
 
    // Get Location by ID
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found with id: " + id));
    }
 
    // Delete Location by ID
    public String deleteLocationById(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new LocationNotFoundException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
        return "Record deleted Successfully";
    }
}