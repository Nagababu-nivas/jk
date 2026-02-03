package com.example.hrApp.entity;

import jakarta.persistence.*;
import java.util.List;
 
@Entity
@Table(name = "locations")
public class Location {
 
    @Id
    @Column(name = "location_id", nullable = false)
    private Long locationId;
 
    @Column(name = "street_address", length = 40)
    private String streetAddress;
 
    @Column(name = "postal_code", length = 12)
    private String postalCode;
 
    @Column(name = "city", length = 30, nullable = false)
    private String city;
 
    @Column(name = "state_province", length = 25)
    private String stateProvince;
 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;
 
   
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Department> departments;
 
    // Constructors
    public Location() {
    }
 
    public Location(Long locationId, String streetAddress, String postalCode, String city, String stateProvince, Country country) {
        this.locationId = locationId;
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }
 
    // Getters and Setters
    public Long getLocationId() {
        return locationId;
    }
 
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
 
    public String getStreetAddress() {
        return streetAddress;
    }
 
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
 
    public String getPostalCode() {
        return postalCode;
    }
 
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getStateProvince() {
        return stateProvince;
    }
 
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
 
    public Country getCountry() {
        return country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
 
    public List<Department> getDepartments() {
        return departments;
    }
 
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}