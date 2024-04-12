//@@author tayponghee
package classify.commands;

import org.junit.jupiter.api.Test;

import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.SubjectGrade;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentSorterTest {

    public static final String PONG_HEE = "Pong Hee";
    public static final String PARTH = "Parth";
    public static final String YI_XIN = "Yi Xin";
    public static final String GERARD = "Gerard";
    public static final String SHUI_HON = "Shui Hon";
    public static final String SCIENCE = "Science";
    public static final String MATH = "Math";

    /**
     * Test case to verify the sorting of students by different criteria.
     * It tests the sorting functionality based on specified sorting choices.
     */
    @Test
    public void testSortByChoice() {
        ArrayList<Student> students = new ArrayList<>();
        Student student1 = new Student(PONG_HEE);
        Student student2 = new Student(PARTH);
        Student student3 = new Student(YI_XIN);
        Student student4 = new Student(GERARD);
        Student student5 = new Student(SHUI_HON);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);

        //@@author alalal47
        LocalDate student1date = LocalDate.parse("2020-01-13");
        LocalDate student2date = LocalDate.parse("2021-11-13");
        LocalDate student3date = LocalDate.parse("2022-06-13");
        LocalDate student4date = LocalDate.parse("2023-12-13");
        LocalDate student5date = LocalDate.parse("2024-07-13");

        //@@author tayponghee
        StudentAttributes attributes1 = new StudentAttributes(student1);
        attributes1.addSubjectGrade(new SubjectGrade(MATH, 90.0, 10));
        attributes1.addSubjectGrade(new SubjectGrade(SCIENCE, 85.0, 8));
        attributes1.setLastPaymentDate(student1date);

        StudentAttributes attributes2 = new StudentAttributes(student2);
        attributes2.addSubjectGrade(new SubjectGrade(MATH, 88.0, 12));
        attributes2.addSubjectGrade(new SubjectGrade(SCIENCE, 80.0, 7));
        attributes2.setLastPaymentDate(student2date);

        StudentAttributes attributes3 = new StudentAttributes(student3);
        attributes3.addSubjectGrade(new SubjectGrade(MATH, 85.0, 15));
        attributes3.addSubjectGrade(new SubjectGrade(SCIENCE, 82.0, 6));
        attributes3.setLastPaymentDate(student3date);

        StudentAttributes attributes4 = new StudentAttributes(student4);
        attributes4.addSubjectGrade(new SubjectGrade(MATH, 92.0, 14));
        attributes4.addSubjectGrade(new SubjectGrade(SCIENCE, 88.0, 9));
        attributes4.setLastPaymentDate(student4date);

        StudentAttributes attributes5 = new StudentAttributes(student5);
        attributes5.addSubjectGrade(new SubjectGrade(MATH, 85.0, 11));
        attributes5.addSubjectGrade(new SubjectGrade(SCIENCE, 90.0, 10));
        attributes5.setLastPaymentDate(student5date);

        student1.setAttributes(attributes1);
        student2.setAttributes(attributes2);
        student3.setAttributes(attributes3);
        student4.setAttributes(attributes4);
        student5.setAttributes(attributes5);

        StudentSorter.sortByChoice(students, "1", null);
        assertEquals(GERARD, students.get(0).getName());
        assertEquals(PARTH, students.get(1).getName());
        assertEquals(PONG_HEE, students.get(2).getName());
        assertEquals(SHUI_HON, students.get(3).getName());
        assertEquals(YI_XIN, students.get(4).getName());

        StudentSorter.sortByChoice(students, "2", null);
        assertEquals(PONG_HEE, students.get(0).getName());
        assertEquals(PARTH, students.get(1).getName());
        assertEquals(SHUI_HON, students.get(2).getName());
        assertEquals(YI_XIN, students.get(3).getName());
        assertEquals(GERARD, students.get(4).getName());

        //@@author alalal47
        StudentSorter.sortByChoice(students, "3", null);
        assertEquals(PONG_HEE, students.get(0).getName());
        assertEquals(PARTH, students.get(1).getName());
        assertEquals(YI_XIN, students.get(2).getName());
        assertEquals(GERARD, students.get(3).getName());
        assertEquals(SHUI_HON, students.get(4).getName());
    }
}
