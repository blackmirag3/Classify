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

    @Test
    void checkForEmptyNameTestName() throws InvalidCharacterException {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Scanner scanner = new Scanner(PARTH + "\n");

        String name = AddStudent.checkForEmptyName(masterStudentList, scanner, null);

        assertEquals(PARTH, name);
    }

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

    @Test
    void findStudentByNameNonExisting() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Scanner scanner = new Scanner(BANANA_REPUBLIC + "\n");

        Student foundStudent = InputParsing.findStudentByName(masterStudentList, PARTH, scanner);

        assertNull(foundStudent);
    }

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
