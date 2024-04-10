//@@author Cryolian
package classify.student;

import classify.user.NameNumberMatchException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentListTest {

    @Test
    void checkNameNumberPairTest() {
        StudentList studentList = new StudentList();

        Student alpha = new Student("alpha");
        alpha.getAttributes().setPhoneNumber(99999999);

        studentList.addStudent(alpha);

        boolean exceptionThrown = false;

        try {
            StudentList.checkNameNumberPair(studentList, alpha.getName() , alpha.getPhoneNumber());
        } catch (NameNumberMatchException e) {
            exceptionThrown = true;
        } catch (NullPointerException e) {

        }

        assertEquals(studentList.studentList.get(0), alpha);
        assertTrue(exceptionThrown);
    } 

}
