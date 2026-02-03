package com.example.hrApp.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Entity
@Table(name = "regions")
public class Region {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "region_name")
    private String regionName;

    @OneToMany(mappedBy = "region")
    private List<Country> countries;
 
    // Constructors
    public Region() {
    }
 
    public Region(Long regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }
 
    // Getters and Setters
    public Long getRegionId() {
        return regionId;
    }
 
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }
 
    public String getRegionName() {
        return regionName;
    }
 
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
 
    public List<Country> getCountries() {
        return countries;
    }
 
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}