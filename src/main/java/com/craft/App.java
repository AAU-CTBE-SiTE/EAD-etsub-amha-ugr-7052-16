package com.craft;

import jakarta.persistence.*;
import java.util.List;

public class App {

    // Class-wide EntityManagerFactory created once
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // 1. CREATE (Insert)

    public static void createStudent(Student student) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        System.out.println("Student saved successfully!");
    }

    // 2. READ (Select)
    public static Student getStudentById(int id) {
        EntityManager em = getEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }

    public static List<Student> getAllStudents() {
        EntityManager em = getEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }


    // 3. UPDATE
    public static void updateStudentName(String newName, int id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, id);
        if (student != null) {
            student.setName(newName);
        }
        em.getTransaction().commit();
        em.close();
        System.out.println("Student ID " + id + " updated successfully!");
    }

    // 4. DELETE
    public static void deleteStudentById(int id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
            System.out.println("Student ID " + id + " deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
        em.getTransaction().commit();
        em.close();
    }

    // MAIN METHOD TO TEST CRUD

    public static void main(String[] args) {
        // 1. CREATE Test
        Student student1 = new Student();
        student1.setName("John Snow");
        student1.setTech("Java");
        createStudent(student1);

        // 2. READ Test
        System.out.println("Fetched: " + getStudentById(1));

        // 3. UPDATE Test
        updateStudentName("Alemayehu", 1);
        System.out.println("After Update: " + getStudentById(1));

        // 4. DELETE Test (uncomment if you want to test deletion)
        // deleteStudentById(1);

        emf.close();
    }
}