package com.example.hrApp.entity;

import jakarta.persistence.*;
import java.util.List;

 
@Entity
@Table(name = "countries")
public class Country {
 
    @Id
    @Column(name = "country_id", length = 4, nullable = false)
    private String countryId;
 
    @Column(name = "country_name", length = 60)
    private String countryName;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
 
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Location> locations;
 
    // Constructors
    public Country() {
    }
 
    public Country(String countryId, String countryName, Region region) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.region = region;
    }
 
    // Getters and Setters
    public String getCountryId() {
        return countryId;
    }
 
    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
 
    public String getCountryName() {
        return countryName;
    }
 
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
 
    public Region getRegion() {
        return region;
    }
 
    public void setRegion(Region region) {
        this.region = region;
    }
 
    public List<Location> getLocations() {
        return locations;
    }
 
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}