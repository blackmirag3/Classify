package classify.commands;
//@@author tayponghee

import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.SubjectGrade;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
}
