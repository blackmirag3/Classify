package classify.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubjectGradeTest {

    public static final String MATH = "Math";
    public static final String SCIENCE = "Science";
    public static final String ENGLISH = "English";
    public static final String HISTORY = "History";
    public static final String PHYSICS = "Physics";
    public static final String CHEMISTRY = "Chemistry";
    public static final String BIOLOGY = "Biology";

    @Test
    public void testGetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade(MATH, 90.0, 10);
        assertEquals(MATH, subjectGrade.getSubject());
    }

    @Test
    public void testGetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade(SCIENCE, 85.0, 8);
        assertEquals(85.0, subjectGrade.getGrade());
    }

    @Test
    public void testGetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade(ENGLISH, 92.5, 12);
        assertEquals(12, subjectGrade.getClassesAttended());
    }

    @Test
    public void testSetSubject() {
        SubjectGrade subjectGrade = new SubjectGrade(HISTORY, 88.0, 15);
        subjectGrade.setSubject(PHYSICS);
        assertEquals(PHYSICS, subjectGrade.getSubject());
    }

    @Test
    public void testSetGrade() {
        SubjectGrade subjectGrade = new SubjectGrade(CHEMISTRY, 78.0, 10);
        subjectGrade.setGrade(80.5);
        assertEquals(80.5, subjectGrade.getGrade());
    }

    @Test
    public void testSetClassesAttended() {
        SubjectGrade subjectGrade = new SubjectGrade(BIOLOGY, 95.0, 14);
        subjectGrade.setClassesAttended(16);
        assertEquals(16, subjectGrade.getClassesAttended());
    }
}
