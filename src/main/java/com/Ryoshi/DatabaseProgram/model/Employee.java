package com.Ryoshi.DatabaseProgram.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee extends User{

    @Id
    @GeneratedValue
    private long id;

    public Employee() {
    }

    public Employee(long id, String first_name, String last_name, String user_name, String password) {
        super(first_name, last_name, user_name, password);
        this.id = id;
    }

}
