package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // 1. Initialize the Spring IoC Container using the XML config file
        ApplicationContext context = new ClassPathXmlApplicationContext("beansConfig.xml");

        // 2. Retrieve the Student bean from Spring
        Student student = context.getBean("student", Student.class);

        // 3. Print details to verify property & object injections
        student.getDetails();
        System.out.println("Student Name: " + student.getName());

        // 4. Test Bean Scope (Singleton check)
        Student student1 = context.getBean("student", Student.class);
        Student student2 = context.getBean("student", Student.class);

        // This will print 'true' because singleton returns the exact same shared instance
        System.out.println("Are student1 and student2 the exact same instance? " + (student1 == student2));
    }
}