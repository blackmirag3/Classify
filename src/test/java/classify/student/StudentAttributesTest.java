package classify.student;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentAttributesTest {

    @Test
    public void testAddSubjectGrade() {
        Student student = new Student("John Doe");
        StudentAttributes attributes = new StudentAttributes(student);
        SubjectGrade subjectGrade = new SubjectGrade("Math", 90.5, 20);

        attributes.addSubjectGrade(subjectGrade);

        List<SubjectGrade> subjectGrades = attributes.getSubjectGrades();
        assertTrue(subjectGrades.contains(subjectGrade));
    }

    @Test
    public void testGetName() {
        Student student = new Student("John Doe");
        StudentAttributes attributes = new StudentAttributes(student);

        assertEquals("John Doe", attributes.getName());
    }

    @Test
    public void testSetName() {
        Student student = new Student("John Doe");
        StudentAttributes attributes = new StudentAttributes(student);

        attributes.setName("Jane Smith");

        assertEquals("Jane Smith", attributes.getName());
    }

    @Test
    public void testGetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade("Math", 90.5, 20);
        assertEquals("Math", subjectGrade.getSubject());
    }

    @Test
    public void testGetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade("Math", 90.5, 20);
        assertEquals(90.5, subjectGrade.getGrade());
    }

    @Test
    public void testGetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade("Math", 90.5, 20);
        assertEquals(20, subjectGrade.getClassesAttended());
    }

    @Test
    public void testSetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade("Math", 90.5, 20);
        subjectGrade.setSubject("Science");
        assertEquals("Science", subjectGrade.getSubject());
    }

    @Test
    public void testSetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade("Math", 90.5, 20);
        subjectGrade.setGrade(85.0);
        assertEquals(85.0, subjectGrade.getGrade());
    }

    @Test
    public void testSetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade("Math", 90.5, 20);
        subjectGrade.setClassesAttended(25);
        assertEquals(25, subjectGrade.getClassesAttended());
    }
}

