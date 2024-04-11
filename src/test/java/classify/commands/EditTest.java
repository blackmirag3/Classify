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
    private static final String BYE = "bye";
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
}
