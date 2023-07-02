package com.Ryoshi.DatabaseProgram.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToOne
    private User user;

}
