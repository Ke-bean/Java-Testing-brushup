package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import com.studentmgtsystem.model.Semester;
import com.studentmgtsystem.service.SemesterService;
public class SaveSemesterTest {
    private SemesterService semesterService;
    @Before
    public void setUp() {
        semesterService = new SemesterService();
    }
    @Test
    public void testSaveSemester() {
        String semesterName = "SummerSemester" + UUID.randomUUID().toString().substring(0, 8);
        String startDate = "2024-06-01";
        String endDate = "2024-08-25";
        semesterService.saveSemester(semesterName, startDate, endDate);
        Semester retrievedSemester = semesterService.getSemesterByName(semesterName);
        assertNotNull("Retrieved semester should not be null", retrievedSemester);
        assertEquals("Semester name should match", semesterName, retrievedSemester.getSemester_name());
        assertEquals("Start date should match", startDate, retrievedSemester.getStart_date());
        assertEquals("End date should match", endDate, retrievedSemester.getEnd_date());
    }
}
