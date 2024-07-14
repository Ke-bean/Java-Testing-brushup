package HibernateUtil;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.studentmgtsystem.model.Student;
import com.studentmgtsystem.model.StudentRegistration;
import com.studentmgtsystem.model.Semester;
import com.studentmgtsystem.model.Course;
import com.studentmgtsystem.model.StudentCourses;
import com.studentmgtsystem.model.Department;

public class ConfigUtil {
    private static SessionFactory sessionFactory;
    
    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        
        Properties properties = new Properties();
        
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/student_management_db");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "kebean");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, "true");
        
        configuration.setProperties(properties);
        
        return configuration;
    }
    
    public static SessionFactory getSessionFactory() {
        
        try {
            if (sessionFactory == null) {
                Configuration configuration = getConfiguration();
                
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(StudentRegistration.class);
                configuration.addAnnotatedClass(Semester.class);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(StudentCourses.class);
                configuration.addAnnotatedClass(Department.class);
                
                sessionFactory = configuration.buildSessionFactory();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sessionFactory;
    }
}
