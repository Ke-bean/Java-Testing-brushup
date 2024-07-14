package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.studentmgtsystem.model.Course;
import com.studentmgtsystem.model.Semester;
import com.studentmgtsystem.service.CourseService;
import com.studentmgtsystem.service.SemesterService;

public class saveCourseTest {
    private CourseService courseService;
    private SemesterService semesterService;

    @Before
    public void setUp() {
        courseService = new CourseService();
        semesterService = new SemesterService();
    }
    @Test
    public void testSaveCourse() {
        String semesterName = "SummerSemester" + UUID.randomUUID().toString().substring(0, 8);
        String startDate = "2024-06-01";
        String endDate = "2024-08-25";
        semesterService.saveSemester(semesterName, startDate, endDate);
        Semester semester = semesterService.getSemesterByName(semesterName);
        String courseCode = "SENG 8325" + UUID.randomUUID().toString().substring(0, 5);
        String courseName = "Software Testing " + UUID.randomUUID().toString().substring(0, 8);
        courseService.saveCourse(courseCode, courseName, semester);
        Course retrievedCourse = courseService.getCourseByCode(courseCode);
        assertNotNull("Retrieved course should not be null", retrievedCourse);
        assertEquals("Course code should match", courseCode, retrievedCourse.getCourse_code());
        assertEquals("Course name should match", courseName, retrievedCourse.getCourse_name());
        assertNotNull("Course semester should not be null", retrievedCourse.getSemester());
        assertEquals("Semester name should match", semesterName, retrievedCourse.getSemester().getSemester_name());
    }
}
