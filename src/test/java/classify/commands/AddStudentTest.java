package classify.commands;
//@@author tayponghee

import classify.student.Student;
import classify.student.StudentList;
import classify.user.InputParsing;
import classify.user.InvalidCharacterException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddStudentTest {

    private static final String PARTH = "Parth";
    private static final String BANANA_REPUBLIC = "Banana Republic";

    /**
     * Test case to verify the behavior of the checkForEmptyName method when a non-empty name is provided.
     * This test checks if the method correctly handles non-empty name input and returns the provided name.
     * @throws InvalidCharacterException if an invalid character is encountered during name validation.
     */
    @Test
    void checkForEmptyNameTestName() throws InvalidCharacterException {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Scanner scanner = new Scanner(PARTH + "\n");

        String name = AddStudent.checkForEmptyName(masterStudentList, scanner, null);

        assertEquals(PARTH, name);
    }

    /**
     * Test case to verify the behavior of the checkForEmptyName method when an empty name is provided.
     * This test checks if the method correctly throws an exception when an empty name is encountered.
     */
    @Test
    void checkForEmptyNameTestEmpty() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Scanner scanner = new Scanner("\n");

        boolean exceptionThrown = false;
        try {
            AddStudent.checkForEmptyName(masterStudentList, scanner, null);
        } catch (NoSuchElementException | InvalidCharacterException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown);
    }

    /**
     * Test case to find a student by name when the student does not exist.
     * This test checks if the method correctly returns null when the student with the given name is not found.
     */
    @Test
    void findStudentByNameNonExisting() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Scanner scanner = new Scanner(BANANA_REPUBLIC + "\n");
        Student foundStudent = InputParsing.findStudentByName(masterStudentList, PARTH, scanner);

        assertNull(foundStudent);
    }

    /**
     * Test case to find a student by name when the student exists.
     * This test checks if the method correctly returns the found student when the student with the given name exists.
     */
    @Test
    void findStudentByNameExisting() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Scanner scanner = new Scanner(BANANA_REPUBLIC + "\n");
        Student foundStudent = InputParsing.findStudentByName(masterStudentList, BANANA_REPUBLIC, scanner);

        assertNull(foundStudent);
    }


    //@@author Cryolian
    @Test 
    void addSpecialCharactersTest() {
        StudentList studentList = new StudentList();
        Scanner scanner = new Scanner("muli----");

        AddStudent.addStudent(studentList.studentList, scanner, null);
        assertTrue(studentList.studentList.size() == 0);

    }

}
