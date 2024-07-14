package com.studentmgtsystem.model;

import jakarta.persistence.*;
import java.util.UUID;
@Entity
public class Semester {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String semester_name;
    
    @Column(nullable = false)
    private String start_date;
    
    @Column(nullable = false)
    private String end_date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSemester_name() {
        return semester_name;
    }

    public void setSemester_name(String semester_name) {
        this.semester_name = semester_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String startDate) {
        this.start_date = startDate;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String endDate) {
        this.end_date = endDate;
    }
}