package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // 1. Initialize Spring IoC Container using Java Configuration (Annotations)
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Retrieve Student bean and test collection injection
        Student student = context.getBean("student", Student.class);
        student.getDetails();

        System.out.println("----------------------------------------");

        // 3. Test Prototype Scope
        Student student1 = context.getBean("student", Student.class);
        Student student2 = context.getBean("student", Student.class);

        System.out.println("Are student1 and student2 the exact same instance? " + (student1 == student2));

        student1.setName("Jane Doe");
        System.out.println("student1 name: " + student1.getName());
        System.out.println("student2 name (should remain John Doe): " + student2.getName());
    }
}