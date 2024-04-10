//@@author tayponghee
package classify.commands;

import org.junit.jupiter.api.Test;

import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.SubjectGrade;

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

        StudentAttributes attributes1 = new StudentAttributes(student1);
        attributes1.addSubjectGrade(new SubjectGrade(MATH, 90.0, 10));
        attributes1.addSubjectGrade(new SubjectGrade(SCIENCE, 85.0, 8));

        StudentAttributes attributes2 = new StudentAttributes(student2);
        attributes2.addSubjectGrade(new SubjectGrade(MATH, 88.0, 12));
        attributes2.addSubjectGrade(new SubjectGrade(SCIENCE, 80.0, 7));

        StudentAttributes attributes3 = new StudentAttributes(student3);
        attributes3.addSubjectGrade(new SubjectGrade(MATH, 85.0, 15));
        attributes3.addSubjectGrade(new SubjectGrade(SCIENCE, 82.0, 6));

        StudentAttributes attributes4 = new StudentAttributes(student4);
        attributes4.addSubjectGrade(new SubjectGrade(MATH, 92.0, 14));
        attributes4.addSubjectGrade(new SubjectGrade(SCIENCE, 88.0, 9));

        StudentAttributes attributes5 = new StudentAttributes(student5);
        attributes5.addSubjectGrade(new SubjectGrade(MATH, 85.0, 11));
        attributes5.addSubjectGrade(new SubjectGrade(SCIENCE, 90.0, 10));

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
    }
}
