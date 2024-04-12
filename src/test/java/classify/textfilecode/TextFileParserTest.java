package classify.textfilecode;
//@@author ParthGandhiNUS

import classify.student.Student;
import classify.student.SubjectGrade;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextFileParserTest {
    public static final String JOHN_DOE = "John_Doe";
    public static final Integer NORMAL_SG_NUMBER = 99999999;
    public static final String MATH = "Math";
    public static final String SCIENCE = "Science";
    public static final double MATH_GRADE = 81.3;
    public static final Integer MATH_CLASSES = 10;
    private static final String INVALID_PHONE_NUMBER_MESSAGE="'s phone number is not a valid Singapore phone number." +
        " Please edit this students' phone number with the edit command!";

    @Test
    public void testAddPhoneNumberValidCase() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);

        TextFileParser.addPhoneNumber(JOHN_DOE, student, NORMAL_SG_NUMBER);

        assertEquals((int) NORMAL_SG_NUMBER, student.getAttributes().getPhoneNumber());
    }

    @Test
    public void testAddPhoneNumberInvalidCase() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);

        TextFileParser.addPhoneNumber(JOHN_DOE, student, 989898);
        assertEquals(JOHN_DOE+INVALID_PHONE_NUMBER_MESSAGE, outContent.toString().trim());
    }

    @Test
    public void testMatchingStudentIndexAllTrue() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);
        student.getAttributes().setPhoneNumber(NORMAL_SG_NUMBER);

        boolean checker = TextFileParser.matchingStudentIndex(masterStudentList,
                JOHN_DOE, NORMAL_SG_NUMBER) == 0;
        assertTrue(checker);
    }

    @Test
    public void testMatchingStudentIndexWrongNumber() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);
        student.getAttributes().setPhoneNumber(NORMAL_SG_NUMBER);

        boolean checker = TextFileParser.matchingStudentIndex(masterStudentList,
                JOHN_DOE, 98989898) == 0;
        assertFalse(checker);
    }

    @Test
    public void testMatchingStudentIndexWrongName() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);
        student.getAttributes().setPhoneNumber(NORMAL_SG_NUMBER);

        boolean checker = TextFileParser.matchingStudentIndex(masterStudentList,
                "Jane Doe", NORMAL_SG_NUMBER) == 0;
        assertFalse(checker);
    }


    @Test
    public void testMatchingSubjectTrueCase() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);
        student.getAttributes().setPhoneNumber(NORMAL_SG_NUMBER);
        SubjectGrade newSubject = new SubjectGrade(MATH, MATH_GRADE, MATH_CLASSES);
        student.getAttributes().addSubjectGrade(newSubject);
        

        assertTrue(TextFileParser.matchingSubject(masterStudentList, MATH, 0));
    }

    @Test
    public void testMatchingSubjectFalseCase() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);
        student.getAttributes().setPhoneNumber(NORMAL_SG_NUMBER);
        SubjectGrade newSubject = new SubjectGrade(MATH, MATH_GRADE, MATH_CLASSES);
        student.getAttributes().addSubjectGrade(newSubject);
        

        assertFalse(TextFileParser.matchingSubject(masterStudentList, SCIENCE, 0));
    }

    @Test
    public void testMatchingNameNumberTrueCase() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student(JOHN_DOE);
        masterStudentList.add(student);
        student.getAttributes().setPhoneNumber(NORMAL_SG_NUMBER);

        assertTrue(TextFileParser.matchingNameNumber(masterStudentList, JOHN_DOE, NORMAL_SG_NUMBER));
    }

    @Test
    public void testMatchingNameNumberFalseCase() {
        ArrayList<Student> masterStudentList = new ArrayList<>();
        Student student = new Student("Jane Doe");
        masterStudentList.add(student);
        student.getAttributes().setPhoneNumber(98989898);

        assertFalse(TextFileParser.matchingNameNumber(masterStudentList, JOHN_DOE, NORMAL_SG_NUMBER));
    }
}
