package com.studentmgtsystem.model;

import jakarta.persistence.*;
import java.util.UUID;
@Entity
public class Student {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String date_of_birth;
    
    @Column(nullable = false)
    private String first_name;
    
    @Column(nullable = false)
    private String last_name;
    
    @Column(nullable = false)
    private String gender;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getData_of_birth() {
        return date_of_birth;
    }

    public void setData_of_birth(String data_of_birth) {
        this.date_of_birth = data_of_birth;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}