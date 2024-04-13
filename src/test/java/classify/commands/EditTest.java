package classify.commands;

import classify.student.Student;
import classify.student.StudentList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

//Integration testing for "edit" implementation within Classify
public class EditTest {
    private static ArrayList<Student> masterList;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Initialises masterList by referencing static masterStudentList to run edit commands on
     */
    @BeforeAll
    public static void initialiseList() {
        masterList = StudentList.masterStudentList;
    }

    /**
     * Clears master student list before each test
     * Redirect system output to a PrintStream for capturing test output
     */
    @BeforeEach
    public void setUp() {
        masterList.clear();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Clears static masterStudentList
     */
    @AfterEach
    public void tearDown() {
        masterList.clear();
    }

    /**
     * Tests edit command where masterList is not empty and user tries to find a student that does not exist
     */
    @Test
    public void testEditNoStudentFound() {

        String testStudent = "adam";
        Student student = new Student(testStudent);
        ArrayList<Student> students = StudentList.masterStudentList;
        students.add(student);

        String testInput = "not adam";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));

        EditStudent.editStudent(new Scanner(System.in), null);

        //Initial function call where programme prompts for student name
        System.setOut(System.out);
        String printedOutput = outputStream.toString();
        String expectedOutput = "Name of student to edit (blank to exit):" + System.lineSeparator() +
                "No student found!" + System.lineSeparator();
        assertEquals(printedOutput, expectedOutput);
    }

    //@@author Cryolians
    @Test
    void testInvalidNumberEdit() {
        String commandString = "88888888 \n" +
                "4 \n" +
                "99999999 \n" +
                '\n';

        Student alpha = new Student("Alpha");
        alpha.getAttributes().setPhoneNumber(88888888);
        StudentList.masterStudentList.add(alpha);

        Student alpha2 = new Student("Alpha");
        alpha2.getAttributes().setPhoneNumber(99999999);
        StudentList.masterStudentList.add(alpha2);

        assertEquals(StudentList.masterStudentList.size(), 2);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner input = new Scanner(commandString);

        System.setOut(new PrintStream(output));

        EditStudent.editStudent(input, "Alpha");

        assertEquals(88888888, alpha.getPhoneNumber());
    }

    @Test
    void testValidNumberEdit() {
        String commandString = "88888888 \n" +
                "4 \n" +
                "80808080 \n" +
                '\n';

        Student alpha = new Student("Alpha");
        alpha.getAttributes().setPhoneNumber(88888888);
        StudentList.masterStudentList.add(alpha);

        Student alpha2 = new Student("Alpha");
        alpha2.getAttributes().setPhoneNumber(99999999);
        StudentList.masterStudentList.add(alpha2);

        assertEquals(StudentList.masterStudentList.size(), 2);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner input = new Scanner(commandString);

        System.setOut(new PrintStream(output));

        EditStudent.editStudent(input, "Alpha");

        assertEquals(80808080, alpha.getPhoneNumber());
    }

    @Test
    void testInvalidGenderEdit() {
        String commandString = "7 \n" +
                "----~~~~ \n" +
                '\n';

        Student alpha = new Student("Alpha");
        alpha.getAttributes().setGender("gender");
        StudentList.masterStudentList.add(alpha);

        assertEquals(StudentList.masterStudentList.size(), 1);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner input = new Scanner(commandString);

        System.setOut(new PrintStream(output));

        EditStudent.editStudent(input, "Alpha");

        assertEquals("gender", alpha.getGender());
    }

    @Test
    void testValidGenderEdit() {
        String commandString = "7 \n" +
                "gender2\n" +
                '\n';

        Student alpha = new Student("Alpha");
        alpha.getAttributes().setGender("gender");
        StudentList.masterStudentList.add(alpha);

        assertEquals(StudentList.masterStudentList.size(), 1);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner input = new Scanner(commandString);

        System.setOut(new PrintStream(output));

        EditStudent.editStudent(input, "Alpha");

        assertEquals("gender2", alpha.getGender());
    }

}
