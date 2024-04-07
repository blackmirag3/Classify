package classify.commands;

import java.util.ArrayList;
import java.util.Scanner;

import classify.data.DataHandler;
import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.StudentList;
import classify.student.SubjectGrade;
import classify.user.InputParsing;
import classify.user.NameNumberMatchException;
import classify.ui.UI;

//@@author blackmirag3
public class EditStudent extends Commands {
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String FIVE = "5";
    private static final String SIX = "6";
    private static final String DEFAULT_STRING_VALUE = "Unknown";

    public static void editStudent(Scanner in, String name) {
        Student student = getStudent(in, name);
        if (student == null) {
            UI.printStudentNotFound();
            return;
        }
        editStudentDetails(in, student);
    }

    private static Student getStudent(Scanner in, String name) {

        ArrayList<Student> list = StudentList.masterStudentList;
        if (list.isEmpty()) {
            UI.printEmptyListError();
            return null;
        }

        if (name == null) {
            System.out.println("Name of student to edit (blank to exit):");
            name = in.nextLine().trim();
        }

        //quick exit clause
        if (name.isBlank()) {
            return null;
        }

        return InputParsing.findStudentByName(list, name, in);
    }

    private static void editStudentDetails(Scanner in, Student student) {

        StudentAttributes attributes = student.getAttributes();
        ViewStudent.showStudentInfo(student);
        UI.printEditPrompt();

        while (true) {
            String command = in.nextLine().trim();

            if (command.isBlank()) {
                System.out.println("Exiting edit.");
                UI.printDivider();
                return;
            }

            switch (command) {

            case ONE:
                AddStudent.addSubject(in, attributes);
                student.setAttributes(attributes);
                return;

            case TWO:
                editSubject(in, attributes);
                return;

            case THREE:
                deleteSubject(in, attributes);
                return;

            case FOUR:
                editNumber(in, attributes);
                return;

            case FIVE:
                editPaymentDate(in, attributes);
                return;

            case SIX:
                editRemarks(in, attributes);
                return;

            default:
                break;
            }
            DataHandler.writeStudentInfo();
        }
    }

    //@@author alalal47
    /**
     * Edits the last payment date for a particular student
     *
     * @param in               The scanner object to read user input.
     * @param attributes       The attributes object of the student that the user has chosen to edit.
     */
    //@@author Cryolian
    private static void editPaymentDate(Scanner in, StudentAttributes attributes) {
        UI.println("Current stored date: " + attributes.getLastPaymentDate());
        attributes.setLastPaymentDate(InputParsing.readInDate(in));
    }

    //@@author alalal47
    /**
     * Edits the last payment date for a particular student
     *
     * @param in               The scanner object to read user input.
     * @param attributes       The attributes object of the student that the user has chosen to edit.
     */
    //@@author Cryolian
    private static void editRemarks(Scanner in, StudentAttributes attributes) {
        UI.println(attributes.getRemarks());
        UI.printDivider();
        UI.println("Enter blank to stop editing.");

        String newRemarks = InputParsing.readInString(in);

        if (newRemarks.equals(DEFAULT_STRING_VALUE)) {
            return;
        }

        attributes.setRemarks(newRemarks);
        UI.printDivider();
    }

    //@@author alalal47
    /**
     * Edits the phone number for a particular student
     *
     * @param in               The scanner object to read user input.
     * @param attributes       The attributes object of the student that the user has chosen to edit.
     */
    //@@author Cryolian
    private static void editNumber(Scanner in, StudentAttributes attributes) {
        try {
            openNumberEditInterface(in, attributes);
            UI.println("Number updated successfully: " + String.valueOf(attributes.getPhoneNumber()));
        } catch (NameNumberMatchException e) {
            UI.println("Please try again with a different number.");
            UI.println("Another student of the same name was found with the same number.");
        } catch (NumberFormatException e) {
            UI.println("Exiting number edit.");
        }
        UI.printDivider();
    }

    //@@author alalal47
    /**
     * Displays the interface to guide the user on how to edit the phone number for a particular student.
     * Displays the current number and instructions to stop editing
     *
     * @param in               The scanner object to read user input.
     * @param attributes       The attributes object of the student that the user has chosen to edit.
     */
    //@@author Cryolian
    private static void openNumberEditInterface(Scanner in, StudentAttributes attributes)
            throws NameNumberMatchException, NumberFormatException {

        UI.printDivider();
        UI.println("Current Number: " + String.valueOf(attributes.getPhoneNumber()));
        UI.println("Type in and enter the letter a if you wish to stop editing.");
        int number = InputParsing.promptForPhoneNumber(in);
        //@@author ParthGandhiNUS
        if (number != attributes.getPhoneNumber()){
            StudentList.checkNameNumberPair(StudentList.masterStudentList, attributes.getName(), number);
        }
        //@@author Cryolian
        attributes.setPhoneNumber(number);
    }

    //@@author blackmirag3
    /**
     * Prompts the user to edit subject, grade, and classes attended for student.
     * Continues to prompt the user until user enters blank to exit.
     *
     * @param in         The scanner object to read user input.
     * @param attributes The StudentAttributes object to store the attributes of the
     *                   student.
     */
    private static void editSubject(Scanner in, StudentAttributes attributes) {

        SubjectGrade currentSubject = promptForSubject(in, attributes);
        updateSubjectName(in, attributes, currentSubject);
        updateSubjectGrade(in, currentSubject);
        updateSubjectAttendance(in, currentSubject);
        //System.out.println("Subject updated.");
    }

    private static void updateSubjectAttendance(Scanner in, SubjectGrade currentSubject) {

        if (currentSubject == null) {
            return;
        }
        int newClassesAttended = AddStudent.promptForClassesAttended(in);
        currentSubject.setClassesAttended(newClassesAttended);
    }

    private static void updateSubjectGrade(Scanner in, SubjectGrade currentSubject) {

        if (currentSubject == null) {
            return;
        }
        double newGrade = InputParsing.promptForGrade(in);
        if (newGrade != -1) {
            currentSubject.setGrade(newGrade);
        }
    }

    private static SubjectGrade promptForSubject(Scanner in, StudentAttributes attributes) {

        while (true) {
            System.out.print("Subject to edit (enter nothing to exit): ");
            String subjectToFind = in.nextLine().trim();

            //early exit clause
            if (subjectToFind.isBlank()) {
                System.out.println("No subject edited.");
                return null;
            }

            SubjectGrade currentSubject = attributes.findSubject(subjectToFind);
            
            if (currentSubject == null) {
                System.out.println("Subject not found");
                
            } else {
                return currentSubject;
            }
        }
    }

    private static void updateSubjectName(Scanner in, StudentAttributes attributes,
                                   SubjectGrade currentSubject) {
        if (currentSubject == null) {
            return;
        }
        
        while (true) {
            System.out.print("New subject name (enter nothing to skip): ");
            String newName = in.nextLine().trim();

            //exit clause
            if (newName.isBlank()) {
                return;
            }

            SubjectGrade foundSubject = attributes.findSubject(newName);

            if (foundSubject == currentSubject) {
                System.out.println("New subject name same as current.");

            } else if (foundSubject != null) {
                UI.printSubjectAlreadyExists();

            } else {
                currentSubject.setSubject(newName);
                return;
            }
        }
    }

    //@@author alalal47
    /**
     * Removes the subject that the user inputs from a particular student
     * Continues to prompt the user until user enters blank to exit.
     *
     * @param in               The scanner object to read user input.
     * @param attributes       The attributes object of the student that the user has chosen to edit.
     */
    //@@author blackmirag3
    private static void deleteSubject(Scanner in, StudentAttributes attributes) {
        while (true) {

            System.out.println("Subject to delete (enter blank to exit)");
            String subjectToDelete = in.nextLine().trim();

            //exit clause
            if (subjectToDelete.isBlank()) {
                System.out.println("no subject deleted");
                return;
            }

            if (attributes.findSubject(subjectToDelete) != null) {
                attributes.deleteSubject(subjectToDelete);
                System.out.println("Subject deleted");
            } else {
                System.out.println("subject not found");
            }
        }
    }

}
