package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        // 1. Load the Spring IoC container using our XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("beansConfig.xml");

        // 2. Ask the container for the managed bean (object)
        StudentInt student = context.getBean("student", RegularStudent.class);

        // 3. Call the method
        student.getHello();
    }
}