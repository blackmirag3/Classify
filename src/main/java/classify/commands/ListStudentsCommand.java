package classify.commands;

import classify.student.Student;
import classify.student.StudentAttributes;
import classify.student.StudentList;
import classify.student.SubjectGrade;
import classify.ui.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@@ author blackmirag3
public class ListStudentsCommand {

    private static final String LIST_ALL = "1";
    private static final String LIST_ALL_WITH_CLASSES = "2";
    private static final String LIST_ARCHIVE = "4";
    private static final String LIST_DELETED = "5";
    private static final String LIST_OF_STUDENTS_IN_ARCHIVE =
            "List of students in archive: ";
    private static final String LIST_OF_STUDENTS_IN_RECENTLY_DELETED =
            "List of students in recently deleted: ";
    //@@author tayponghee
    private static final String FULL_STUDENT_LIST = "Full Student list:";
    private static final String LIST_OF_STUDENTS_WITH_TOTAL_CLASSES_ATTENDED =
            "List of students with total classes attended:";
    private static final String INVALID_CHOICE = "Invalid choice!";
    private static final String TOTAL_CLASSES_ATTENDED = " - Total Classes Attended: ";
    private static final String LIST_PHONE_NUMBERS = "3";
    private static final String LIST_OF_STUDENTS_WITH_PHONE_NUMBERS = "List of students with phone numbers:";
    private static final String PHONE_NUM = " - Phone Number: ";
    private static final String INVALID_PHONE_NUMBER = " - Invalid Phone Number! Save file may be corrupt.";


    /**
     * Lets the user view a list of students, optionally filtered by subject.
     * If a subject is provided, it will only list students with that subject.
     * If no subject is provided, it will list all students.
     *
     * @param masterStudentList The list of all students.
     * @param in                The user's input.
     * @param subject           Optional subject to filter the list of students.
     */
    public static void chooseListType(ArrayList<Student> masterStudentList, ArrayList<Student> archiveList,
                                      ArrayList<Student> deletedList,Scanner in, String subject) {
        if (subject != null && !subject.isEmpty()) {
            listStudentsBySubject(masterStudentList, subject);
        } else {
            listAllStudents(masterStudentList, archiveList, deletedList);
        }
    }

    /**
     * Lists all students with the specified subject.
     *
     * @param masterStudentList The list of all students.
     * @param subject           The subject to filter the list of students.
     */
    public static void listStudentsBySubject(ArrayList<Student> masterStudentList, String subject) {
        boolean found = false;
        UI.printStudentsWithSubject(subject);

        for (Student student : masterStudentList) {
            if (student.hasSubject(subject)) {
                int classesAttended = getClassesAttendedForSubject(student, subject);
                if (classesAttended >= 0) {
                    UI.println("- " + student.getName() +
                            " - Classes Attended for " + subject + ": " + classesAttended);
                } else {
                    UI.println("- " + student.getName() + " - " + "No Classes Attended found for " + subject);
                }
                found = true;
            }
        }

        if (!found) {
            UI.printNoStudentsWithSubject(subject);
        }

        UI.printDivider();
    }

    /**
     * Gets the total number of classes attended by a student for the specified subject.
     *
     * @param student The student.
     * @param subject The subject to check for classes attended.
     * @return The number of classes attended for the specified subject.
     */
    private static int getClassesAttendedForSubject(Student student, String subject) {
        int classesAttended = 0;

        StudentAttributes attributes = student.getAttributes();
        List<SubjectGrade> subjectGrades = attributes.getSubjectGrades();

        for (SubjectGrade subjectGrade : subjectGrades) {
            if (subjectGrade.getSubject().equalsIgnoreCase(subject)) {
                if (subjectGrade.getClassesAttended() >= 0){
                    classesAttended += subjectGrade.getClassesAttended();
                } else {
                    classesAttended = -1;
                }
            }
        }

        return classesAttended;
    }

    /**
     * Lists all students in the provided list, giving the user the option to choose the type of list to display.
     *
     * @param masterStudentList The list of all students.
     */
    public static void listAllStudents(ArrayList<Student> masterStudentList,
                                       ArrayList<Student> archiveList, ArrayList<Student> deletedList ) {

        UI.printListAllStudentsChoice();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        //@@ author blackmirag3
        switch (choice) {

        case LIST_ARCHIVE:
            UI.println(LIST_OF_STUDENTS_IN_ARCHIVE);

            StudentList.printCurrentArrayList(archiveList);
            StudentList.printCurrentArrayMessage(archiveList);
            break;

        case LIST_DELETED:
            UI.println(LIST_OF_STUDENTS_IN_RECENTLY_DELETED);

            StudentList.printCurrentArrayList(deletedList);
            StudentList.printCurrentArrayMessage(deletedList);
            break;

        case LIST_ALL:
            UI.println(FULL_STUDENT_LIST);

            StudentList.printCurrentArrayList(masterStudentList);
            //@@ author ParthGandhiNUS
            StudentList.printCurrentArrayMessage(masterStudentList);

            UI.printDivider();
            break;

            //@@author tayponghee
        case LIST_ALL_WITH_CLASSES:
            UI.println(LIST_OF_STUDENTS_WITH_TOTAL_CLASSES_ATTENDED);

            listStudentsWithTotalClasses(masterStudentList);
            StudentList.printCurrentArrayMessage(masterStudentList);
            UI.printDivider();
            break;

        case LIST_PHONE_NUMBERS:
            UI.println(LIST_OF_STUDENTS_WITH_PHONE_NUMBERS);
            listStudentsWithPhoneNumbers(masterStudentList);
            StudentList.printCurrentArrayMessage(masterStudentList);
            UI.printDivider();
            break;

        default:
            UI.println(INVALID_CHOICE);
        }
    }

    //@@author tayponghee
    /**
     * Lists all students in the provided list along with their total classes attended.
     * If the user had left the classes attended blank for that subject,
     * it would not be counted into the total classes attended.
     *
     * @param masterStudentList The list of all students.
     */
    static void listStudentsWithTotalClasses(ArrayList<Student> masterStudentList) {
        int i = 1;
        for (Student student : masterStudentList) {
            int totalClassesAttended = 0;
            
            for (SubjectGrade subjectGrade : student.getAttributes().getSubjectGrades()) {
                if (subjectGrade.getClassesAttended() >= 0) {
                    totalClassesAttended += subjectGrade.getClassesAttended();
                } else {
                    totalClassesAttended += 0;
                }
            }

            System.out.println(i + "." + student.getName() + TOTAL_CLASSES_ATTENDED +
                    totalClassesAttended);
            i++;
        }
    }

    /**
     * Lists all students with their phone numbers.
     *
     * @param masterStudentList The list of all students.
     */
    public static void listStudentsWithPhoneNumbers(ArrayList<Student> masterStudentList) {
        int i = 1;
        for (Student student : masterStudentList) {
            int phoneNumber = student.getAttributes().getPhoneNumber();
            if (isValidPhoneNumber(phoneNumber)) {
                System.out.println(i + "." + student.getName() + PHONE_NUM + phoneNumber);
            } else {
                System.out.println(i + "." + student.getName() + INVALID_PHONE_NUMBER);
            }
            i++;
        }
    }

    /**
     * Checks if a phone number is valid.
     *
     * @param phoneNumber The phone number to be checked.
     * @return true if the phone number is valid, false otherwise.
     */
    private static boolean isValidPhoneNumber(int phoneNumber) {
        return (phoneNumber >= 80000000 && phoneNumber <= 99999999);
    }

}
