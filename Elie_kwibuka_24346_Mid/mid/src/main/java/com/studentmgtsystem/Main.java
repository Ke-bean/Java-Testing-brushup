package com.studentmgtsystem;

import com.studentmgtsystem.model.Course;
import com.studentmgtsystem.model.Department;
import com.studentmgtsystem.model.Semester;
import com.studentmgtsystem.model.Student;
import com.studentmgtsystem.model.StudentRegistration;
import com.studentmgtsystem.service.CourseService;
import com.studentmgtsystem.service.DepartmentService;
import com.studentmgtsystem.service.SemesterService;
import com.studentmgtsystem.service.StudentCoursesService;
import com.studentmgtsystem.service.StudentRegistrationService;
import com.studentmgtsystem.service.StudentService;

import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final SemesterService semesterService = new SemesterService();
    private static final DepartmentService departmentService = new DepartmentService();
    private static final CourseService courseService = new CourseService();
    private static final StudentRegistrationService registrationService = new StudentRegistrationService();
    private static final StudentCoursesService studentCoursesService = new StudentCoursesService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    fetchStudent();
                    break;
                case 3:
                    addSemester();
                    break;
                case 4:
                    addDepartment();
                    break;
                case 5:
                    registerStudentForSemester();
                    break;
                case 6:
                    addCourse();
                    break;
                case 7:
                    saveStudentMarks();
                    break;
                case 8:
                    calculateStudentTotalMarks();
                    break;
                case 9:
                    convertAndClassifyMarks();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Register a new student");
        System.out.println("2. Query Student details");
        System.out.println("3. Register new Semester");
        System.out.println("4. Register new DepartmentDepartment");
        System.out.println("5. Register student for a semester");
        System.out.println("6. Add a new course");
        System.out.println("7. Save student marks for a course");
        System.out.println("8. Student's total marks for semester");
        System.out.println("9. Student's average");
        System.out.println("0. Exit");
        System.out.print("Choose among the choices...: ");
    }

    private static int getUserChoice() {
        return scanner.nextInt();
    }

    private static void registerStudent() {
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter last name: ");
        String lastName = scanner.next();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dob = scanner.next();
        System.out.print("Enter gender: ");
        String gender = scanner.next();

        studentService.saveStudent(firstName, lastName, dob, gender);
        System.out.println("Student registered successfully.");
    }

    private static void fetchStudent() {
        System.out.print("Enter first name: ");
        String firstName = scanner.next();
        System.out.print("Enter last name: ");
        String lastName = scanner.next();

        Student student = studentService.getByFirstNameAndLastName(firstName, lastName);
        if (student != null) {
            System.out.println("Fetched Student: " + student.getFirst_name() + " " + student.getLast_name());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void addSemester() {
        System.out.print("Enter semester name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.next();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.next();

        semesterService.saveSemester(name, startDate, endDate);
        System.out.println("Semester added successfully.");
    }

    private static void registerStudentForSemester() {
        System.out.print("Enter student's first name: ");
        String firstName = scanner.next();
        System.out.print("Enter student's last name: ");
        String lastName = scanner.next();

        Student student = studentService.getByFirstNameAndLastName(firstName, lastName);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter department code: ");
        String departmentCode = scanner.next();
        System.out.print("Enter department name: ");
        scanner.nextLine();
        String departmentName = scanner.nextLine();

        Department department = new Department();
        department.setDepartment_code(departmentCode);
        department.setDepartment_name(departmentName);

        System.out.print("Enter semester name: ");
        String semesterName = scanner.nextLine();
        Semester semester = semesterService.getSemesterByName(semesterName);
        if (semester == null) {
            System.out.println("Semester not found.");
            return;
        }

        System.out.print("Enter registration number: ");
        int registrationNumber = scanner.nextInt();
        System.out.print("Enter registration date (YYYY-MM-DD): ");
        String registrationDate = scanner.next();

        registrationService.registerStudent(student, department, semester, registrationNumber, registrationDate);
        System.out.println("Student registered successfully for the semester.");
    }

    private static void addCourse() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.next();
        System.out.print("Enter course name: ");
        scanner.nextLine();
        String courseName = scanner.nextLine();
        System.out.print("Enter semester name: ");
        String semesterName = scanner.nextLine();

        Semester semester = semesterService.getSemesterByName(semesterName);
        if (semester != null) {
            courseService.saveCourse(courseCode, courseName, semester);
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Semester not found.");
        }
    }

    private static void addDepartment() {
        System.out.print("Enter department code: ");
        String departmenteCode = scanner.next();
        System.out.print("Enter department name: ");
        scanner.nextLine();
        String departmentName = scanner.nextLine();
        System.out.print("Enter semester name: ");
        departmentService.registerDepartment(departmentName, departmenteCode);
        System.out.println("Department registered successfully.");
    }
    private static void saveStudentMarks() {
        System.out.print("Enter student registration number: ");
        int registrationNumber = scanner.nextInt();

        StudentRegistration registration = registrationService.getRegistrationByNumber(registrationNumber);
        if (registration == null) {
            System.out.println("Student registration not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.next();
        Course course = courseService.getCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        System.out.print("Enter marks for the course: ");
        int marks = scanner.nextInt();

        studentCoursesService.saveStudentMarks(registration, course, marks);
        System.out.println("Student marks saved successfully.");
    }

    private static void calculateStudentTotalMarks() {
        System.out.print("Enter student registration number: ");
        int registrationNumber = scanner.nextInt();

        double totalMarks = studentCoursesService.calculateTotalMarks(registrationNumber);

        System.out.printf("Student's total marks for the semester: %.2f out of 100\n", totalMarks);
    }

    private static void convertAndClassifyMarks() {
        System.out.print("Enter student registration number: ");
        int registrationNumber = scanner.nextInt();
        System.out.println(registrationNumber);
        double totalMarks = studentCoursesService.calculateTotalMarks(registrationNumber);
        double marksOn20Scale = studentCoursesService.convertTo20Scale(totalMarks);
        String classification = studentCoursesService.getGradeClassification(marksOn20Scale);

        System.out.printf("Student's total marks: %.2f out of 100\n", totalMarks);
        System.out.printf("Converted to 20 scale: %.2f out of 20\n", marksOn20Scale);
        System.out.printf("Classification: %s\n", classification);
    }
}
