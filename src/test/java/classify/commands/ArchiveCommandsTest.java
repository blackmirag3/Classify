package classify.commands;

import org.junit.jupiter.api.Test;
import classify.student.StudentList;
import classify.student.Student;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

//@@author Cryolian
public class ArchiveCommandsTest {
    
    @Test
    void testValidArchiveCommand() {
        StudentList studentlist = new StudentList();

        studentlist.addStudent(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.archiveStudent(studentlist.studentList, StudentList.archiveList, "wario", in);

        assertTrue(studentlist.studentList.size() == 0);
        assertTrue(StudentList.archiveList.size() == 1);

    }

    @Test
    void testInvalidArchiveCommand() {
        StudentList studentlist = new StudentList();

        studentlist.addStudent(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.archiveStudent(studentlist.studentList, StudentList.archiveList, "notWario", in);

        assertTrue(studentlist.studentList.size() == 1);
        assertTrue(StudentList.archiveList.size() == 0);

    }

    @Test
    void testDuplicateArchiveCommand() {
        StudentList studentlist = new StudentList();

        studentlist.addStudent(new Student("Wario"));
        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.archiveStudent(studentlist.studentList, StudentList.archiveList, "notWario", in);

        assertTrue(studentlist.studentList.size() == 1);
        assertTrue(StudentList.archiveList.size() == 1);

    }

    @Test
    void testValidUnarchiveCommand() {
        StudentList studentlist = new StudentList();

        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.unarchiveStudent(studentlist.studentList, StudentList.archiveList, "wario", in);

        assertTrue(studentlist.studentList.size() == 1);
        assertTrue(StudentList.archiveList.size() == 0);

    }

    @Test
    void testInvalidUnarchiveCommand() {
        StudentList studentlist = new StudentList();

        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.unarchiveStudent(studentlist.studentList, StudentList.archiveList, "notWario", in);

        assertTrue(studentlist.studentList.size() == 0);
        assertTrue(StudentList.archiveList.size() == 1);

    }

    @Test
    void testDuplicateUnrchiveCommand() {
        StudentList studentlist = new StudentList();

        studentlist.addStudent(new Student("Wario"));
        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.unarchiveStudent(studentlist.studentList, StudentList.archiveList, "notWario", in);

        assertTrue(studentlist.studentList.size() == 1);
        assertTrue(StudentList.archiveList.size() == 1);

    }
}
