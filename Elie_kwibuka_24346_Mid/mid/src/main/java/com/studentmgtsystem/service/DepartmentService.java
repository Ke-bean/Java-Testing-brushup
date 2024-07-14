package com.studentmgtsystem.service;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.studentmgtsystem.model.Department;
import HibernateUtil.ConfigUtil;
public class DepartmentService {
    public void registerDepartment(String departmentCode, String departmentName) {
        try (Session session = ConfigUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Department department = new Department();
            department.setDepartment_code(departmentCode);
            department.setDepartment_name(departmentName);
            session.save(department);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
