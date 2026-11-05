package com.craft;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;

@Component("student")
@Scope("prototype")
@Data
@ToString
public class Student {
    private int id = 101;
    private String name = "John Doe";
    private int age = 20;
    private Address address;
    private List<Subject> subjects;

    public Student() {
        System.out.println("Student Default Constructor Called");
    }

    // Constructor Injection using annotations (@Autowired)
    @Autowired
    public Student(Address address, List<Subject> subjects) {
        this.address = address;
        this.subjects = subjects;
        System.out.println("Student Parameterized Constructor (Annotation Injection) Called");
    }

    public void getDetails() {
        System.out.println("Student Name: " + this.getName());
        int index = 1;
        if (subjects != null) {
            for (Subject subject : subjects) {
                System.out.println("Subject " + index + ": " + subject.getSubjectName());
                index++;
            }
        }
        System.out.println("Full Details: " + this);
    }
}