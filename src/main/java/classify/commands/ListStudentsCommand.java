package classify.commands;

import classify.student.Student;
import classify.student.StudentList;
import classify.student.SubjectGrade;
import classify.ui.UI;

import java.util.ArrayList;
import java.util.Scanner;

//@@ author blackmirag3
public class ListStudentsCommand {

    private static final String LIST_ALL = "1";
    private static final String LIST_ALL_WITH_CLASSES = "2";
    private static final String LIST_ARCHIVE = "3";
    private static final String LIST_DELETED = "4";
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
                System.out.println("- " + student.getName());
                found = true;
            }
        }

        if (!found) {
            UI.printNoStudentsWithSubject(subject);
        }

        UI.printDivider();
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
    private static void listStudentsWithTotalClasses(ArrayList<Student> masterStudentList) {
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
}
