package classify.commands;
//@@author tayponghee

import classify.student.Student;
import classify.student.StudentAttributes;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListStudentsWithPhoneNumbersTest {

    private static final String YIXIN = "Yi Xin";
    private static final String GERARD = "Gerard";

    @Test
    void testListStudentsWithValidNumbers() {
        ArrayList<Student> students = new ArrayList<>();
        Student student1 = new Student(YIXIN);
        StudentAttributes attributes1 = new StudentAttributes(student1);
        attributes1.setPhoneNumber(87654321);
        student1.setAttributes(attributes1);
        students.add(student1);

        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("1." + YIXIN + " - Phone Number: 87654321");

        assertEquals(expectedOutput, captureOutput(() -> ListStudentsCommand.listStudentsWithPhoneNumbers(students)));
    }

    @Test
    void testListStudentsWithInvalidNumbers() {
        ArrayList<Student> students = new ArrayList<>();
        Student student1 = new Student(GERARD);
        StudentAttributes attributes1 = new StudentAttributes(student1);
        attributes1.setPhoneNumber(1234567);
        student1.setAttributes(attributes1);
        students.add(student1);

        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("1." + GERARD + " - Invalid Phone Number! Save file may be corrupt.");

        assertEquals(expectedOutput, captureOutput(() -> ListStudentsCommand.listStudentsWithPhoneNumbers(students)));
    }

    private List<String> captureOutput(Runnable action) {
        List<String> output = new ArrayList<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        action.run();

        System.out.flush();
        System.setOut(originalOut);
        output.add(byteArrayOutputStream.toString().trim());
        return output;
    }
}
