package com.Ryoshi.DatabaseProgram.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.Set;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;
    @NotBlank
    private String street;
    @NotBlank
    private String home;
    private String district;
    private int postalCode;
    @NotBlank
    private String number;
    @OneToMany(mappedBy = "owner")
    private Set<Dogs> dogs;

    public Owner(long id,
                 String first_name,
                 String last_name,
                 String street,
                 String home,
                 String district,
                 int postalCode,
                 String number,
                 Set<Dogs> dogs) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.street = street;
        this.home = home;
        this.district = district;
        this.postalCode = postalCode;
        this.number = number;
        this.dogs = dogs;

        /*
        first_name
        last_name
        street
        home
        district
        postalCode
        */

    }

    public Owner() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public Set<Dogs> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dogs> dogs) {
        this.dogs = dogs;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
