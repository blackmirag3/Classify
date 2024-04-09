package classify.student;
//@@author tayponghee

import classify.commands.ViewStudent;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewStudentTest {

    private static final String XIAOMING = "Xiao Ming";

    @Test
    void viewStudentTest() {
        InputStream in = new ByteArrayInputStream(XIAOMING.getBytes());
        Scanner scanner = new Scanner(in);

        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(XIAOMING);
        StudentAttributes attributes = new StudentAttributes(student);
        List<SubjectGrade> subjectGrades = new ArrayList<>();
        subjectGrades.add(new SubjectGrade("Computer Science", 85.0, 20));
        attributes.addSubjectGrade(subjectGrades.get(0));
        student.setAttributes(attributes);
        masterStudentList.add(student);

        ViewStudent.viewStudent(masterStudentList, scanner, null);

        assertEquals(XIAOMING, student.getName());
        assertEquals(85.0, student.getAttributes().getSubjectGrades().get(0).getGrade());
        assertEquals(20, student.getAttributes().getSubjectGrades().get(0).getClassesAttended());
        //currently, does not include the details like phone number and last paid date.
    }
}
