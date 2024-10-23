package com.example.ppro3project.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Driver {

    private int id = -1;

    @NotBlank(message = "Name is required")
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 90, message = "Age must not exceed 90")
    private int age;

    @Min(value = 10000, message = "Salary must be at least 10 000")
    @Max(value = 80000, message = "Salary must not exceed 80 000")
    private int salary;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
