package com.example.mvp_applicatin_for_controlling_personal.Entities;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
///
public class Department
{
    private Long id;
    private String name;

    public Department(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
