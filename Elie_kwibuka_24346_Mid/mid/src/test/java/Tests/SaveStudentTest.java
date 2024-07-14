package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.studentmgtsystem.model.Student;
import com.studentmgtsystem.service.StudentService;

public class SaveStudentTest {
    private StudentService studentService;

    @Before
    public void setUp() {
        studentService = new StudentService();
    }

    @Test
    public void testSaveAndRetrieveStudent() {
        String firstName = "Elie" + UUID.randomUUID().toString().substring(0, 8);
        String lastName = "Kwibuka" + UUID.randomUUID().toString().substring(0, 8);
        String dateOfBirth = "1940-01-01";
        String gender = "Male";

        studentService.saveStudent(firstName, lastName, dateOfBirth, gender);

        Student retrievedStudent = studentService.getByFirstNameAndLastName(firstName, lastName);

        assertNotNull("Student should exists", retrievedStudent);
        assertEquals("First name should match", firstName, retrievedStudent.getFirst_name());
        assertEquals("Last name should match", lastName, retrievedStudent.getLast_name());
        assertEquals("Date of birth should match", dateOfBirth, retrievedStudent.getData_of_birth());
        assertEquals("Gender should match", gender, retrievedStudent.getGender());
    }

    @Test
    public void testGetByFirstNameAndLastNameNonExistent() {
        String firstName = "NonExistent" + UUID.randomUUID().toString().substring(0, 8);
        String lastName = "Person" + UUID.randomUUID().toString().substring(0, 8);

        Student nonExistentStudent = studentService.getByFirstNameAndLastName(firstName, lastName);
        assertNull("Non-existent student should return null", nonExistentStudent);
    }
}
