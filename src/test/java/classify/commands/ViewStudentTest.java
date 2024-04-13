package classify.commands;
//@@author tayponghee

import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.SubjectGrade;
import classify.ui.UI;
import classify.student.StudentList;

import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewStudentTest {

    private static final String XIAO_MING = "Xiao Ming";

    @Test
    void viewStudentTest() {
        InputStream in = new ByteArrayInputStream(XIAO_MING.getBytes());
        Scanner scanner = new Scanner(in);

        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(XIAO_MING);
        StudentAttributes attributes = new StudentAttributes(student);
        List<SubjectGrade> subjectGrades = new ArrayList<>();
        subjectGrades.add(new SubjectGrade("Computer Science", 85.0, 20));
        attributes.addSubjectGrade(subjectGrades.get(0));
        student.setAttributes(attributes);
        masterStudentList.add(student);

        ViewStudent.viewStudent(masterStudentList, scanner, null);

        assertEquals(XIAO_MING, student.getName());
        assertEquals(85.0, student.getAttributes().getSubjectGrades().get(0).getGrade());
        assertEquals(20, student.getAttributes().getSubjectGrades().get(0).getClassesAttended());
        //currently, does not include the details like phone number and last paid date.
    }

    //@@author Cryolian
    @Test
    void viewNonExistingStudent() {
        StudentList studentlist = new StudentList();

        assertEquals(studentlist.studentList.size(), 0);

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));

        ViewStudent.viewStudent(studentlist.studentList, null, "mario");
        String expectedOutput = "No student found!";
        String printedOutput = output.toString().trim();

        assertEquals(expectedOutput, printedOutput);

    }

    @Test
    void viewMultipleStudentsOfTheSameName() {
        StudentList studentlist = new StudentList();

        Student alpha = new Student("Alpha");
        alpha.getAttributes().setPhoneNumber(88888888);
        studentlist.addStudent(alpha);

        Student alpha2 = new Student("Alpha");
        alpha2.getAttributes().setPhoneNumber(99999999);
        studentlist.addStudent(alpha2);

        assertEquals(studentlist.studentList.size(), 2);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner input = new Scanner("88888888\n");

        System.setOut(new PrintStream(output));

        ViewStudent.viewStudent(studentlist.studentList, input, "alpha");
        String expectedOutput = "Name found in existing student. " +
                "Please make sure the entered phone number will be unique." + System.lineSeparator() +
                "Alpha: 88888888" + System.lineSeparator() +
                "Alpha: 99999999" + System.lineSeparator() + 
                "Please input a valid phone number: " + System.lineSeparator() +
                "Enter a non-number or blank to exit interface." + System.lineSeparator() +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator() +
                "Student details: " + System.lineSeparator() +
                "Name: Alpha" + System.lineSeparator() + System.lineSeparator() + 
                "Phone Number: 88888888" + System.lineSeparator() +
                "Gender: null" + System.lineSeparator() +
                "Last Payment Date: 2024-04-13" + System.lineSeparator() +
                "Remarks: NA" + System.lineSeparator() +  
                "No subjects and grades found for this student." + System.lineSeparator() +
                "Total classes attended across all subjects: 0"; 

        expectedOutput = expectedOutput.trim();
        String printedOutput = output.toString().trim();

        assertEquals(expectedOutput.length(), printedOutput.length());
    }
}
