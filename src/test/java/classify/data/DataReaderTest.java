package classify.data;
//@@author ParthGandhiNUS

import classify.student.Student;
import classify.student.SubjectGrade;
import classify.user.InvalidCharacterException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataReaderTest {
    public static final String JOHN_DOE = "John Doe";
    public static final String GENDER = "male";
    public static final Integer PHONE_NUMBER = 98989898;
    public static final String LAST_PAYMENT_DATE = "2024-04-13";
    public static final String REMARKS = "Na";
    public static final String MATH = "Math";
    public static final double MATH_GRADE = 81.3;
    public static final Integer MATH_CLASSES = 10;
    public static final String VALID_SUBJECT_INPUT = "Math ## 81.3 ## 10";
    public static final String SCIENCE = "Science";
    public static final double SCIENCE_GRADE = 95.0;
    public static final Integer SCIENCE_CLASSES = 12;
    public static final String FULL_VALID_INPUT = "John Doe ~~ male ~~ 98989898 ~~ 2024-04-13 ~~ Na ~~ Math " +
            "## 81.3 ## 10 #--#";
    public static final String FULL_INVALID_INPUT = "Jane Doe ~~ female ~~ 88888888 ~~ 2022-02-11 ~~ Nope ~~ " +
            "Science ## 95.0 ## 12 #--#";


    @Test
    public void restoreStudentListValidTest(){
        Student expectedStudent = new Student(JOHN_DOE);
        expectedStudent.getAttributes().setGender(GENDER);
        expectedStudent.getAttributes().setPhoneNumber(PHONE_NUMBER);

        LocalDate lastPayment = DataReader.convertStringToLocalDate(LAST_PAYMENT_DATE);
        expectedStudent.getAttributes().setLastPaymentDate(lastPayment);
        expectedStudent.getAttributes().setRemarks(REMARKS);

        SubjectGrade newSubject = new SubjectGrade(MATH, MATH_GRADE, MATH_CLASSES);
        expectedStudent.getAttributes().addSubjectGrade(newSubject);

        ArrayList<Student> actualStudentList = new ArrayList<>();
        DataReader.restoreStudentList(FULL_VALID_INPUT, actualStudentList);
        Student actualStudent = actualStudentList.get(0);

        SubjectGrade expectedStudentSubject = expectedStudent.getAttributes().getSubjectGrades().get(0);
        SubjectGrade actualStudentSubject = actualStudent.getAttributes().getSubjectGrades().get(0);

        assertEquals(expectedStudent.getName(), actualStudent.getName());
        assertEquals(expectedStudent.getGender(), actualStudent.getGender());
        assertEquals(expectedStudent.getPhoneNumber(), actualStudent.getPhoneNumber());
        assertEquals(expectedStudent.getLastPaymentDate(), actualStudent.getLastPaymentDate());
        assertEquals(expectedStudent.getAttributes().getRemarks(), actualStudent.getAttributes().getRemarks());
        assertEquals(expectedStudentSubject.getSubject(),actualStudentSubject.getSubject());
        assertEquals(expectedStudentSubject.getGrade(),actualStudentSubject.getGrade());
        assertEquals(expectedStudentSubject.getClassesAttended(),actualStudentSubject.getClassesAttended());
    }

    @Test
    public void restoreStudentListInvalidTest(){
        Student expectedStudent = new Student(JOHN_DOE);
        expectedStudent.getAttributes().setGender(GENDER);
        expectedStudent.getAttributes().setPhoneNumber(PHONE_NUMBER);

        LocalDate lastPayment = DataReader.convertStringToLocalDate(LAST_PAYMENT_DATE);
        expectedStudent.getAttributes().setLastPaymentDate(lastPayment);
        expectedStudent.getAttributes().setRemarks(REMARKS);

        SubjectGrade newSubject = new SubjectGrade(MATH, MATH_GRADE, MATH_CLASSES);
        expectedStudent.getAttributes().addSubjectGrade(newSubject);

        ArrayList<Student> actualStudentList = new ArrayList<>();
        DataReader.restoreStudentList(FULL_INVALID_INPUT, actualStudentList);
        Student actualStudent = actualStudentList.get(0);

        SubjectGrade expectedStudentSubject = expectedStudent.getAttributes().getSubjectGrades().get(0);
        SubjectGrade actualStudentSubject = actualStudent.getAttributes().getSubjectGrades().get(0);

        assertNotEquals(expectedStudent.getName(), actualStudent.getName());
        assertNotEquals(expectedStudent.getGender(), actualStudent.getGender());
        assertNotEquals(expectedStudent.getPhoneNumber(), actualStudent.getPhoneNumber());
        assertNotEquals(expectedStudent.getLastPaymentDate(), actualStudent.getLastPaymentDate());
        assertNotEquals(expectedStudent.getAttributes().getRemarks(), actualStudent.getAttributes().getRemarks());
        assertNotEquals(expectedStudentSubject.getSubject(),actualStudentSubject.getSubject());
        assertNotEquals(expectedStudentSubject.getGrade(),actualStudentSubject.getGrade());
        assertNotEquals(expectedStudentSubject.getClassesAttended(),actualStudentSubject.getClassesAttended());
    }

    @Test
    public void getAllSubjectInformationValidTest() throws InvalidCharacterException {
        SubjectGrade expectedSubject = new SubjectGrade(MATH.trim(), MATH_GRADE, MATH_CLASSES);
        SubjectGrade actualSubject = DataReader.getAllSubjectInformation(VALID_SUBJECT_INPUT);
        assertEquals(expectedSubject.getSubject(), actualSubject.getSubject());
        assertEquals(expectedSubject.getGrade(), actualSubject.getGrade());
        assertEquals(expectedSubject.getClassesAttended(),actualSubject.getClassesAttended());
    }

    @Test
    public void getAllSubjectInformationInvalidSubjectTest() throws InvalidCharacterException {
        SubjectGrade expectedSubject = new SubjectGrade(SCIENCE.trim(), MATH_GRADE, MATH_CLASSES);
        SubjectGrade actualSubject = DataReader.getAllSubjectInformation(VALID_SUBJECT_INPUT);
        assertNotEquals(expectedSubject.getSubject(), actualSubject.getSubject());
        assertEquals(expectedSubject.getGrade(), actualSubject.getGrade());
        assertEquals(expectedSubject.getClassesAttended(),actualSubject.getClassesAttended());
    }

    @Test
    public void getAllSubjectInformationInvalidGradeTest() throws InvalidCharacterException {
        SubjectGrade expectedSubject = new SubjectGrade(MATH.trim(), SCIENCE_GRADE, MATH_CLASSES);
        SubjectGrade actualSubject = DataReader.getAllSubjectInformation(VALID_SUBJECT_INPUT);
        assertEquals(expectedSubject.getSubject(), actualSubject.getSubject());
        assertNotEquals(expectedSubject.getGrade(), actualSubject.getGrade());
        assertEquals(expectedSubject.getClassesAttended(),actualSubject.getClassesAttended());
    }

    @Test
    public void getAllSubjectInformationInvalidClassesTest() throws InvalidCharacterException {
        SubjectGrade expectedSubject = new SubjectGrade(MATH.trim(), MATH_GRADE, SCIENCE_CLASSES);
        SubjectGrade actualSubject = DataReader.getAllSubjectInformation(VALID_SUBJECT_INPUT);
        assertEquals(expectedSubject.getSubject(), actualSubject.getSubject());
        assertEquals(expectedSubject.getGrade(), actualSubject.getGrade());
        assertNotEquals(expectedSubject.getClassesAttended(),actualSubject.getClassesAttended());
    }

    @Test
    public void convertStringToLocalDateValidTest(){
        String dateToConvert = "2024-01-01";
        LocalDate convertedDate = DataReader.convertStringToLocalDate(dateToConvert);
        LocalDate expectedDate =  LocalDate.of(2024,1,1);
        boolean isValidConversion = (Objects.equals(convertedDate, expectedDate));
        assertTrue(isValidConversion);
    }

    @Test
    public void convertStringToLocalDateInvalidTest(){
        String dateToConvert = "2023-01-01";
        LocalDate convertedDate = DataReader.convertStringToLocalDate(dateToConvert);
        LocalDate expectedDate =  LocalDate.of(2024,1,1);
        boolean isValidConversion = (Objects.equals(convertedDate, expectedDate));
        assertFalse(isValidConversion);
    }

    @Test
    public void convertStringToLocalDateInvalidDateTest(){
        String dateToConvert = "2023-02-30";
        LocalDate convertedDate = DataReader.convertStringToLocalDate(dateToConvert);
        LocalDate expectedDate =  LocalDate.of(2024,1,1);
        boolean isValidConversion = (Objects.equals(convertedDate, expectedDate));
        assertFalse(isValidConversion);
    }

    @Test
    public void checkDeletionRequestInputTest(){
        assertTrue(DataReader.checkDeletionRequestInput("YES"));
    }

    @Test
    public void checkDeletionRequestInvalidTest1(){
        assertFalse(DataReader.checkDeletionRequestInput("yes"));
    }

    @Test
    public void checkDeletionRequestInvalidTest2(){
        assertFalse(DataReader.checkDeletionRequestInput("YEs"));
    }

    @Test
    public void checkDeletionRequestInvalidTest4(){
        assertFalse(DataReader.checkDeletionRequestInput("NO"));
    }

    @Test
    public void deletionRequestFinalConfirmationValidTest(){
        assertTrue(DataReader.deletionRequestFinalConfirmation("CONFIRM"));
    }

    @Test
    public void deletionRequestFinalConfirmationInvalidTest1(){
        assertFalse(DataReader.deletionRequestFinalConfirmation("confirm"));
    }

    @Test
    public void deletionRequestFinalConfirmationInvalidTest2(){
        assertFalse(DataReader.deletionRequestFinalConfirmation("CONfirm"));
    }

    @Test
    public void deletionRequestFinalConfirmationInvalidTest3(){
        assertFalse(DataReader.deletionRequestFinalConfirmation("BANANA"));
    }
}
