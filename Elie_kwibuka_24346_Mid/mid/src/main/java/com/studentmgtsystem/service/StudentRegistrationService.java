package com.studentmgtsystem.service;


import org.hibernate.Session;
import org.hibernate.Transaction;
import HibernateUtil.ConfigUtil;
import jakarta.persistence.TypedQuery;

import com.studentmgtsystem.model.Semester;
import com.studentmgtsystem.model.Student;
import com.studentmgtsystem.model.StudentRegistration;
import com.studentmgtsystem.model.Department;

public class StudentRegistrationService {

    public void registerStudent(Student student, Department department, Semester semester, int registrationNumber,
            String registrationDate) {
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            if (department.getId() == null) {
                session.save(department);
            }
            StudentRegistration registration = new StudentRegistration();
            registration.setStudent(student);
            registration.setDepartment(department);
            registration.setSemester(semester);
            registration.setRegistration_number(registrationNumber);
            registration.setRegistration_date(registrationDate);
            session.save(registration);
            transaction.commit();
        }
    }

    public StudentRegistration getRegistrationByNumber(int registrationNumber) {
        StudentRegistration registration = null;
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TypedQuery<StudentRegistration> query = session.createQuery(
                    "FROM StudentRegistration WHERE registration_number = :registrationNumber",
                    StudentRegistration.class);
            query.setParameter("registrationNumber", registrationNumber);
            registration = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registration;
    }

}

