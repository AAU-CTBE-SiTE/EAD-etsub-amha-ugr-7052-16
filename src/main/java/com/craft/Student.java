package com.craft;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Student {
    private int id;
    private String name;
    private int age;
    private String city;
    private String country;
    private Address address;

    // Default Constructor (used for Setter/Literal Injection)
    public Student() {
        System.out.println("Student Default Constructor Called");
    }

    // Parameterized Constructor (used for Constructor Injection)
    public Student(int id, String name, int age, Address address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        System.out.println("Student Parameterized Constructor Called");
    }

    public void getDetails() {
        System.out.println("Student Details: " + this);
    }
}