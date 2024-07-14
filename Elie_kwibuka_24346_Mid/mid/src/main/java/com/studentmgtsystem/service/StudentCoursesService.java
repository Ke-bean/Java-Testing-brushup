package com.studentmgtsystem.service;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import HibernateUtil.ConfigUtil;
import jakarta.persistence.TypedQuery;
import com.studentmgtsystem.model.StudentCourses;
import com.studentmgtsystem.model.StudentRegistration;
import com.studentmgtsystem.model.Course;
public class StudentCoursesService {

    public void saveStudentMarks(StudentRegistration registration, Course course, int marksInCourse) {
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            StudentCourses studentCourse = new StudentCourses();
            studentCourse.setRegistration(registration);
            studentCourse.setCourse(course);
            studentCourse.setMarks_in_course(marksInCourse);
            session.save(studentCourse);
            transaction.commit();
        }
    }
    public double calculateTotalMarks(int registrationNumber) {
        double totalMarks = 0;
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            
            String hql = "FROM StudentCourses sc WHERE sc.registration.registration_number = :regNumber";
            TypedQuery<StudentCourses> query = session.createQuery(hql, StudentCourses.class);
            query.setParameter("regNumber", registrationNumber);
            
            List<StudentCourses> studentCourses = query.getResultList();
            
            for (StudentCourses sc : studentCourses) {
                totalMarks += sc.getMarks_in_course();
            }
            
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (totalMarks / (5 * 20)) * 100;
    }
    public double convertTo20Scale(double totalMarks) {
        return (totalMarks / 100) * 20;
    }

    public String getGradeClassification(double marksOn20Scale) {
        if (marksOn20Scale >= 16) {
            return "High Distinction";
        } else if (marksOn20Scale >= 12) {
            return "Lower Distinction";
        } else if (marksOn20Scale >= 10) {
            return "Pass";
        } else {
            return "Expel";
        }
    }
}
