package classify.commands;

import classify.student.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteCommandsTest {

    private static final String TEST_STUDENT = "Test Student";

    /**
     * Test case to verify the functionality of deleting a student.
     * This test checks if the deleteStudent method correctly removes a student from the master student list
     * and adds it to the recently deleted list.
     */
    @Test
    void testDeleteStudent() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        Student testStudent = new Student(TEST_STUDENT);
        masterStudentList.add(testStudent);

        DeleteCommands.deleteStudent(masterStudentList, recentlyDeletedList, scanner, TEST_STUDENT);

        assertFalse(masterStudentList.contains(testStudent));
        assertTrue(recentlyDeletedList.contains(testStudent));
    }

    /**
     * Test case to verify the functionality of restoring a student.
     * This test checks if the restoreStudent method correctly adds a student back to the master student list
     * and removes it from the recently deleted list.
     */
    @Test
    void testRestoreStudent() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        Student testStudent = new Student(TEST_STUDENT);
        recentlyDeletedList.add(testStudent);

        DeleteCommands.restoreStudent(masterStudentList, recentlyDeletedList, scanner, TEST_STUDENT);

        assertTrue(masterStudentList.contains(testStudent));
        assertFalse(recentlyDeletedList.contains(testStudent));
    }

    /**
     * Test case to verify the functionality of undoing a student deletion.
     * This test checks if the undoDelete method correctly restores the latest deleted student
     * from the recently deleted list to the master student list.
     */
    @Test
    void testUndoDelete() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();

        Student testStudent = new Student(TEST_STUDENT);
        recentlyDeletedList.add(testStudent);

        DeleteCommands.undoDelete(masterStudentList, recentlyDeletedList);

        assertTrue(masterStudentList.contains(testStudent));
        assertFalse(recentlyDeletedList.contains(testStudent));
    }
}
