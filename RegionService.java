package com.example.hrApp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hrApp.entity.Region;
import com.example.hrApp.exception.RegionNotFoundException;
import com.example.hrApp.Repo.RegionRepository;
 
@Service
public class RegionService {
 
    @Autowired
    private RegionRepository regionRepository;
 
    // Add new Region
    public String saveRegion(Region region) {
        if (region == null) {
            throw new IllegalArgumentException("Region data cannot be null");
        }
        regionRepository.save(region);
        return "Record Created Successfully";
    }
 
    // Update existing Region
    public String updateRegion(Region region) {
        if (region == null || !regionRepository.existsById(region.getRegionId())) {
            throw new RegionNotFoundException("Region not found with id: " + region.getRegionId());
        }
        regionRepository.save(region);
        return "Record Modified Successfully";
    }
 
    // Get all Regions
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }
 
    // Get Region by ID
    public Region getRegionById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new RegionNotFoundException("Region not found with id: " + id));
    }
 
    // Delete Region by ID
    public String deleteRegionById(Long id) {
        if (!regionRepository.existsById(id)) {
            throw new RegionNotFoundException("Region not found with id: " + id);
        }
        regionRepository.deleteById(id);
        return "Record deleted Successfully";
    }
}