package classify.student;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentAttributesTest {

    public static final String JOHN_DOE = "John Doe";
    public static final String MATH = "Math";
    public static final String JANE_SMITH = "Jane Smith";
    public static final String SCIENCE = "Science";

    @Test
    public void testAddSubjectGrade() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);

        attributes.addSubjectGrade(subjectGrade);

        List<SubjectGrade> subjectGrades = attributes.getSubjectGrades();
        assertTrue(subjectGrades.contains(subjectGrade));
    }

    @Test
    public void testGetName() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);

        assertEquals(JOHN_DOE, attributes.getName());
    }

    @Test
    public void testSetName() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);

        attributes.setName(JANE_SMITH);

        assertEquals(JANE_SMITH, attributes.getName());
    }

    @Test
    public void testGetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        assertEquals(MATH, subjectGrade.getSubject());
    }

    @Test
    public void testGetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        assertEquals(90.5, subjectGrade.getGrade());
    }

    @Test
    public void testGetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        assertEquals(20, subjectGrade.getClassesAttended());
    }

    @Test
    public void testSetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        subjectGrade.setSubject(SCIENCE);
        assertEquals(SCIENCE, subjectGrade.getSubject());
    }

    @Test
    public void testSetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        subjectGrade.setGrade(85.0);
        assertEquals(85.0, subjectGrade.getGrade());
    }

    @Test
    public void testSetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        subjectGrade.setClassesAttended(25);
        assertEquals(25, subjectGrade.getClassesAttended());
    }
}

