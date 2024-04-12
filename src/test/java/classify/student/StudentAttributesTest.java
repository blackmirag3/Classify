package classify.student;
//@@author tayponghee

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentAttributesTest {

    public static final String JOHN_DOE = "John Doe";
    public static final String MATH = "Math";
    public static final String JANE_SMITH = "Jane Smith";
    public static final String SCIENCE = "Science";

    /**
     * Test case to verify the addition of a subject grade to the StudentAttributes.
     * It checks if the subject grade is successfully added to the attributes.
     */
    @Test
    public void testAddSubjectGrade() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);

        attributes.addSubjectGrade(subjectGrade);

        List<SubjectGrade> subjectGrades = attributes.getSubjectGrades();
        assertTrue(subjectGrades.contains(subjectGrade));
    }

    /**
     * Test case to verify getting the name of the student from the StudentAttributes.
     * It checks if the correct name is returned.
     */
    @Test
    public void testGetName() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);

        assertEquals(JOHN_DOE, attributes.getName());
    }

    /**
     * Test case to verify setting the name of the student in the StudentAttributes.
     * It checks if the name is correctly set.
     */
    @Test
    public void testSetName() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);

        attributes.setName(JANE_SMITH);

        assertEquals(JANE_SMITH, attributes.getName());
    }

    /**
     * Test case to verify getting the subject of the subject grade.
     * It checks if the correct subject is returned.
     */
    @Test
    public void testGetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        assertEquals(MATH, subjectGrade.getSubject());
    }

    /**
     * Test case to verify getting the grade of the subject grade.
     * It checks if the correct grade is returned.
     */
    @Test
    public void testGetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        assertEquals(90.5, subjectGrade.getGrade());
    }

    /**
     * Test case to verify getting the number of classes attended of the subject grade.
     * It checks if the correct number of classes attended is returned.
     */
    @Test
    public void testGetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        assertEquals(20, subjectGrade.getClassesAttended());
    }

    /**
     * Test case to verify setting the subject of the subject grade.
     * It checks if the subject is correctly set.
     */
    @Test
    public void testSetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        subjectGrade.setSubject(SCIENCE);
        assertEquals(SCIENCE, subjectGrade.getSubject());
    }

    /**
     * Test case to verify setting the grade of the subject grade.
     * It checks if the grade is correctly set.
     */
    @Test
    public void testSetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        subjectGrade.setGrade(85.0);
        assertEquals(85.0, subjectGrade.getGrade());
    }
    
    /**
     * Test case to verify setting the number of classes attended of the subject grade.
     * It checks if the number of classes attended is correctly set.
     */
    @Test
    public void testSetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);
        subjectGrade.setClassesAttended(25);
        assertEquals(25, subjectGrade.getClassesAttended());
    }

    /**
     * Test case to verify finding a subject grade by subject name.
     * It checks if the correct subject grade is returned.
     */
    @Test
    public void testFindSubject() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);

        attributes.addSubjectGrade(subjectGrade);

        SubjectGrade foundSubject = attributes.findSubject(MATH);

        assertEquals(subjectGrade, foundSubject);
    }

    /**
     * Test case to verify deleting a subject grade by subject name.
     * It checks if the subject grade is successfully deleted.
     */
    @Test
    public void testDeleteSubject() {
        Student student = new Student(JOHN_DOE);
        StudentAttributes attributes = new StudentAttributes(student);
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.5, 20);

        attributes.addSubjectGrade(subjectGrade);

        attributes.deleteSubject(MATH);

        assertTrue(attributes.getSubjectGrades().isEmpty());
    }

}
