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

        StudentList.masterStudentList.clear();
        StudentList.archiveList.clear();
        StudentList.masterStudentList.add(new Student("Wario"));
        Scanner in = new Scanner("Wario\n");
        assertTrue(StudentList.masterStudentList.size() == 1);

        ArchiveCommands.archiveStudent(StudentList.masterStudentList, StudentList.archiveList, null, in);

        assertTrue(StudentList.masterStudentList.size() == 0);
        assertTrue(StudentList.archiveList.size() == 1);

    }

    @Test
    void testInvalidArchiveCommand() {
        StudentList.masterStudentList.clear();
        StudentList.archiveList.clear();

        StudentList.masterStudentList.add(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.archiveStudent(StudentList.masterStudentList, StudentList.archiveList, "notWario", in);

        assertTrue(StudentList.masterStudentList.size() == 1);
        assertTrue(StudentList.archiveList.size() == 0);

    }

    @Test
    void testDuplicateArchiveCommand() {

        StudentList.masterStudentList.clear();
        StudentList.archiveList.clear();

        StudentList.masterStudentList.add(new Student("Wario"));
        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.archiveStudent(StudentList.masterStudentList, StudentList.archiveList, "notWario", in);

        assertTrue(StudentList.masterStudentList.size() == 1);
        assertTrue(StudentList.archiveList.size() == 1);

    }

    @Test
    void testValidUnarchiveCommand() {
        StudentList studentlist = new StudentList();
        StudentList.masterStudentList.clear();
        StudentList.archiveList.clear();

        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("");

        ArchiveCommands.unarchiveStudent(studentlist.studentList, StudentList.archiveList, "wario", in);

        assertTrue(studentlist.studentList.size() == 1);
        assertTrue(StudentList.archiveList.size() == 0);

    }

    @Test
    void testInvalidUnarchiveCommand() {
        StudentList studentlist = new StudentList();

        StudentList.masterStudentList.clear();
        StudentList.archiveList.clear();

        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("notWario\n");

        ArchiveCommands.unarchiveStudent(studentlist.studentList, StudentList.archiveList, null, in);

        assertTrue(studentlist.studentList.size() == 0);
        assertTrue(StudentList.archiveList.size() == 1);

    }

    @Test
    void testDuplicateUnarchiveCommand() {
        StudentList studentlist = new StudentList();

        StudentList.masterStudentList.clear();
        StudentList.archiveList.clear();

        studentlist.addStudent(new Student("Wario"));
        StudentList.archiveList.add(new Student("Wario"));
        Scanner in = new Scanner("Wario\n");

        ArchiveCommands.unarchiveStudent(studentlist.studentList, StudentList.archiveList, null, in);

        assertTrue(studentlist.studentList.size() >= 0);
        assertTrue(StudentList.archiveList.size() == 0);

    }
}
