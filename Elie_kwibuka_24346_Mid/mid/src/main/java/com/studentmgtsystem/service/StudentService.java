package com.studentmgtsystem.service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import HibernateUtil.ConfigUtil;
import jakarta.persistence.TypedQuery;
import com.studentmgtsystem.model.Student;

public class StudentService {

    public void saveStudent(String firstName, String lastName, String dateOfBirth, String gender) {
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Student student = new Student();
            student.setFirst_name(firstName);
            student.setLast_name(lastName);
            student.setData_of_birth(dateOfBirth);
            student.setGender(gender);

            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getByFirstNameAndLastName(String firstName, String lastName) {
        Student student = null;
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TypedQuery<Student> query = session.createQuery(
                    "FROM Student WHERE first_name = :firstName AND last_name = :lastName",
                    Student.class);
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            student = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
}
