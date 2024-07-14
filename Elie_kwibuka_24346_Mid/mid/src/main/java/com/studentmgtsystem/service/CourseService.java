package com.studentmgtsystem.service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import HibernateUtil.ConfigUtil;
import jakarta.persistence.TypedQuery;
import com.studentmgtsystem.model.Semester;
import com.studentmgtsystem.model.Course;
public class CourseService {

    public void saveCourse(String courseCode, String courseName, Semester semester) {
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Course course = new Course();
            course.setCourse_code(courseCode);
            course.setCourse_name(courseName);
            course.setSemester(semester);
            session.save(course);
            transaction.commit();
        }
    }

    public Course getCourseByCode(String courseCode) {
        Course course = null;
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TypedQuery<Course> query = session.createQuery("FROM Course WHERE course_code = :courseCode", Course.class);
            query.setParameter("courseCode", courseCode);
            course = query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }
}
