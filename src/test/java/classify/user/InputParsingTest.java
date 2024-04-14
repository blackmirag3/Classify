package classify.user;

import classify.student.Student;
import classify.student.StudentList;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputParsingTest {

    //@@author tayponghee
    @Test
    public void testByeCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        ArrayList<Student> archiveList = new ArrayList<>();
        String [] commands = new String[2];
        commands[0] = "bye";
        InputParsing.parseUserCommand(commands, masterStudentList, recentlyDeletedList,
                archiveList, new Scanner(System.in));

        System.setOut(System.out);

        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "Hope you've had a productive day. See you again! Goodbye!" +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        assertEquals(expectedOutput.trim(), printedOutput);
    }

    @Test
    public void testListCommandWithInvalidArgument() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        ArrayList<Student> archiveList = new ArrayList<>();
        String[] commands = new String[2];
        commands[0] = "list";
        commands[1] = "random_argument";
        InputParsing.parseUserCommand(commands, masterStudentList, recentlyDeletedList,
                archiveList, new Scanner(System.in));

        System.setOut(System.out);

        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "Type 'list' only!";


        assertEquals(expectedOutput.trim(), printedOutput);
    }

    @Test
    public void testDeleteCommandWithNonExistentStudent() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        ArrayList<Student> archiveList = new ArrayList<>();
        String[] commands = new String[2];
        commands[0] = "delete";
        commands[1] = "NonExistentStudent";
        InputParsing.parseUserCommand(commands, masterStudentList, recentlyDeletedList,
                archiveList, new Scanner(System.in));

        System.setOut(System.out);

        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "Student not found! Cannot be deleted." +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        assertEquals(expectedOutput.trim(), printedOutput);
    }

    //@@author blackmirag3
    @Test
    public void testUnknownCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        ArrayList<Student> archiveList = new ArrayList<>();
        String [] commands = new String[2];
        commands[0] = "yeet";
        InputParsing.parseUserCommand(commands, students, recentlyDeletedList, archiveList, new Scanner(System.in));
        System.setOut(System.out);
        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "No such command, type \"help\" to view all commands" +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        assertEquals(expectedOutput.trim(), printedOutput);
    }

    @Test
    public void testEditCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        ArrayList<Student> archiveList = new ArrayList<>();
        String [] commands = new String[2];
        commands[0] = "edit";

        InputParsing.parseUserCommand(commands, students, recentlyDeletedList, archiveList, new Scanner(System.in));

        System.setOut(System.out);
        String printedOutput = outputStream.toString();
        String expectedOutput = "Currently no students in list." + System.lineSeparator() +
                "No student found!" + System.lineSeparator();

        assertEquals(expectedOutput, printedOutput);
    }

    //@@author alalal47
    @Test
    public void testHelpCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        ArrayList<Student> recentlyDeletedList = new ArrayList<>();
        ArrayList<Student> archiveList = new ArrayList<>();
        String [] commands = new String[2];
        commands[0] = "help";
        InputParsing.parseUserCommand(commands, masterStudentList, recentlyDeletedList,
                archiveList, new Scanner(System.in));

        System.setOut(System.out);

        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "add" + "\n" +
                                "    " + "Adds a student to the student list," + "\n" +
                                "    " + "expects a name, grade and lessons attended" + "\n" +
                                "    " + "e.g. add [name]" + System.lineSeparator() +
                                System.lineSeparator() +
                                "edit" + "\n" +
                                "    " + "Edits a students details, expects a name," + "\n" +
                                "    " + "can be used directly with a name" + "\n" +
                                "    " + "e.g. edit [name]" + System.lineSeparator() +
                                System.lineSeparator() +
                                "view" + "\n" +
                                "    " + "Views a students details, expects a name," + "\n" +
                                "    " + "can be used directly with a name" + "\n" +
                                "    " + "e.g. view [name]" + System.lineSeparator() +
                                System.lineSeparator() +
                                "delete" + "\n" +
                                "    " + "Deletes a student from the student list," + "\n" +
                                "    " + "expects a name," + "\n" +
                                "    " + "can be used directly with a name" + "\n" +
                                "    " + "e.g. delete [name]" + System.lineSeparator() +
                                System.lineSeparator()+
                                "restore" + "\n" +
                                "    " + "Restore a student deleted within the" + "\n" +
                                "    " + "current session, expects a name," + "\n" +
                                "    " + "can be used directly by restore [name]." + System.lineSeparator() +
                                System.lineSeparator() +
                                "undo" + "\n" +
                                "    " + "Restores the last student deleted" + "\n" +
                                "    " + "in the current session." + System.lineSeparator() +
                                System.lineSeparator() +
                                "list" + "\n" +
                                "    " + "Displays the list of all students" + System.lineSeparator() +
                                "    " + "Currently available types:" + "\n" +
                                "    " + "Whole student list" + "\n" +
                                "    " + "With total classes attended" + "\n" +
                                "    " + "With phone number" + System.lineSeparator() +
                                "    " + "The archived list" + "\n" +
                                "    " + "The recently deleted list" + "\n" +
                                "    " + "By certain subject only" + System.lineSeparator() +
                                System.lineSeparator() +
                                "bye" + "\n" +
                                "    " + "Exits Classify" + System.lineSeparator() +
                                System.lineSeparator() +
                                "sort" + "\n" +
                                "    " + "Sorts the student list by the" + "\n" +
                                "    " + "input parameter, expects an attribute" + "\n" +
                                "    " + "to sort by," + "\n" +
                                "    " + "can be used directly by sort [type]." + System.lineSeparator() +
                                "    " + "Currently available types:" + "\n" +
                                "    " + "Name" + "\n" +
                                "    " + "Classes" + "\n" +
                                "    " + "Payment" + System.lineSeparator() +
                                System.lineSeparator() +
                                "archive" + "\n" +
                                "    " + "Removes the specified student from" + "\n" +
                                "    " + "the list and archives them," + "\n" +
                                "    " + "expects a name, can be used directly by" + "\n" +
                                "    " + "archive [name]." + System.lineSeparator() +
                                System.lineSeparator() +
                                "unarchive" + "\n" +
                                "    " + "Removes the specified student from the" + "\n" +
                                "    " + "archive and adds them to the list," + "\n" +
                                "    " + "expects a name, can be used directly by" + "\n" +
                                "    " + "unarchive [name]." + System.lineSeparator() +
                                System.lineSeparator() +
                                "process" + "\n" +
                                "    " + "Processes a text file containing" + "\n" +
                                "    " + "a list of students" + "\n" +
                                "    " + "taking the same subject and the" + "\n" +
                                "    " + "same number of classes." + System.lineSeparator() +
                                System.lineSeparator() +
                                "help" + "\n" +
                                "    " + "Prints this help message" + System.lineSeparator() +
                                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        assertEquals(expectedOutput.trim(), printedOutput);
    }

    //@@author Cryolian
    @Test
    public void testReadInDateCommand() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String userInputDate = "2020-12-12";

        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(userInputDate.getBytes()));
            Scanner scanner = new Scanner(System.in);

            LocalDate parsedDate = InputParsing.readInDate(scanner);
            assertEquals(LocalDate.parse(userInputDate), parsedDate);
        } finally {
            System.setIn(stdin);
        }

        userInputDate = " ";

        try {
            System.setIn(new ByteArrayInputStream(userInputDate.getBytes()));
            Scanner scanner = new Scanner(System.in);

            LocalDate parsedDate = InputParsing.readInDate(scanner);
            assertEquals(LocalDate.now(), parsedDate);
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    public void testParseDateFromString() {
        LocalDate date = InputParsing.parseDateFromString("fiodjsfoi");

        assertEquals(date, LocalDate.now().plusDays(2));

    }

    @Test
    public void findStudentByNameMultipleTest() {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String userInputName = "Alpha";
        String userInputNumber = "88888888";

        System.setIn(new ByteArrayInputStream((userInputNumber).getBytes()));
        Scanner scanner = new Scanner(System.in);

        StudentList studentList = new StudentList();
        Student alpha1 = new Student("Alpha");
        Student alpha2 = new Student("Alpha");

        alpha1.getAttributes().setPhoneNumber(99999999);
        studentList.addStudent(alpha1);
        alpha2.getAttributes().setPhoneNumber(88888888);
        studentList.addStudent(alpha2);

        Student target = InputParsing.findStudentByName(studentList.studentList, userInputName, scanner);
        assertEquals(alpha2, target);
    }

}
