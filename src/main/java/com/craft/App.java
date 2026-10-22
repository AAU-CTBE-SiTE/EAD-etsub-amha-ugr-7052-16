package com.craft;

import jakarta.persistence.*;
import java.util.List;

public class App {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // 1. CREATE (Insert Record)

    public static void createStudent(String name, String dept, double gpa) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        Student student = new Student();
        student.setFullName(name);
        student.setDepartment(dept);
        student.setGpa(gpa);

        em.persist(student);
        em.getTransaction().commit();
        em.close();

        System.out.println("Record created for: " + name);
    }

    // 2. READ (Select Record)
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

    // 3. UPDATE Record
    public static void updateStudentDepartment(int id, String newDept) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        Student student = em.find(Student.class, id);
        if (student != null) {
            student.setDepartment(newDept);
            System.out.println("Updated department for ID " + id + " to " + newDept);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }

        em.getTransaction().commit();
        em.close();
    }

    // 4. DELETE Record
    public static void deleteStudent(int id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();

        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
            System.out.println("Deleted student with ID: " + id);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }

        em.getTransaction().commit();
        em.close();
    }

    // MAIN EXECUTION

    public static void main(String[] args) {
        // 1. CREATE Test Records
        createStudent("Etsub Amha", "Software Engineering", 3.8);
        createStudent("Abebe Bikila", "Computer Science", 3.5);

        // 2. READ Test
        System.out.println("Fetching Student ID 1:");
        Student retrieved = getStudentById(1);
        System.out.println(retrieved);

        // 3. UPDATE Test
        updateStudentDepartment(1, "Enterprise Systems");

        // 4. READ ALL Test
        System.out.println("All Students in DB:");
        List<Student> studentList = getAllStudents();
        for (Student s : studentList) {
            System.out.println(s);
        }

        // 5. DELETE Test (optional execution)
        // deleteStudent(2);

        emf.close();
    }
}