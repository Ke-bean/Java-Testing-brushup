package com.studentmgtsystem.service;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import HibernateUtil.ConfigUtil;
import jakarta.persistence.TypedQuery;
import com.studentmgtsystem.model.Semester;
public class SemesterService {
    public void saveSemester(String semesterName, String startDate, String endDate) {
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Semester semester = new Semester();
            semester.setSemester_name(semesterName);
            semester.setStart_date(startDate);
            semester.setEnd_date(endDate);
            session.save(semester);
            transaction.commit();
        }
    }

    public Semester getSemesterByName(String semesterName) {
        List<Semester> semesters = null;
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            TypedQuery<Semester> query = session.createQuery("FROM Semester WHERE semester_name = :semesterName",
                    Semester.class);
            query.setParameter("semesterName", semesterName);
            semesters = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (semesters != null && !semesters.isEmpty()) {
            return semesters.get(0);
        }
        return null;
    }
}
