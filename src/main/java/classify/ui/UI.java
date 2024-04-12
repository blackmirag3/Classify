package classify.ui;

import classify.student.Student;

import java.util.ArrayList;

//@@author Cryolian
public class UI {
    public static final String TOTAL_CLASSES_ATTENDED_ACROSS_ALL_SUBJECTS = "Total classes " +
            "attended across all subjects: ";
    private static final String EMPTY_SUBJECT_ERROR = "No subjects and grades found for this student.";
    private static final String NULL_ATTRIBUTE_ERROR = "No attributes found for this student.";
    private static final String EMPTY_LIST_ERROR = "Currently no students in list.";
    private static final String VALID_NUMBER_ERROR = "A valid number was not entered. Please input a valid number. ";
    private static final String PHONE_NUMBER_PROMPT = "Please input a valid phone number: ";
    private static final String STUDENT_PHONE_MESSAGE = "Phone Number: ";
    private static final String STUDENT_GENDER_MESSAGE = "Gender: ";
    private static final String STUDENT_PAYMENT_MESSAGE = "Last Payment Date: ";
    private static final String STUDENT_REMARKS_MESSAGE = "Remarks: ";
    private static final String SAME_NAME_ERROR = "Name found in existing student. " + 
            "Please make sure the entered phone number will be unique.";
    private static final String EMPTY_NAME_ERROR = "Student name cannot be empty. Please enter a valid name.";
    private static final String GENDER_PROMPT = "Please input the student's gender: ";
    private static final String PAYMENT_PROMPT = "Please input their last payment date in the format of YYYY-MM-DD. " +
            "Enter blank to input today's date.";
    private static final String REMARKS_PROMPT = "Please input any remarks: ";
    private static final String INVALID_DATE_ERROR = "Please input a valid date in the format YYYY-MM-DD"
            + ", or blank for today.";
    private static final String INVALID_DATE_RANGE = "Please input a date with a reasonable value. \n" +
            "(eg. from 2010 until today.)";
    //@@author ParthGandhiNUS
    private static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String WELCOME_TO_CLASSIFY = "Welcome to Classify!";
    private static final String WHAT_CAN_I_DO_FOR_YOU_TODAY = "What can I do for you today?";
    private static final String CLASSIFY_GOODBYE_MESSAGE = "Hope you've had a productive day. See you again! Goodbye!";
    private static final String WRONG_INPUT_MESSAGE = "No such command, type \"help\" to view all commands";
    //@@author blackmirag3
    private static final String EDIT_COMMAND_PROMPT = "How would you like to edit student? " +
            "Enter index (blank to exit):";
    private static final String EDIT_COMMAND_ADD_SUBJECT_PROMPT = "1. Add subject";
    private static final String EDIT_COMMAND_MODIFY_SUBJECT_PROMPT = "2. Modify subject";
    private static final String EDIT_COMMAND_DELETE_SUBJECT_PROMPT = "3. Delete subject";
    private static final String EDIT_COMMAND_MODIFY_PHONE_NUMBER_PROMPT = "4. Modify phone number";
    private static final String EDIT_COMMAND_MODIFY_REMARKS_PROMPT = "5. Modify remarks";
    private static final String EDIT_COMMAND_MODIFY_PAYMENT_DATE_PROMPT = "6. Modify payment date";
    private static final String EDIT_COMMAND_DELETED_SUBJECT_MESSAGE = "Subject deleted successfully!";
    private static final String EDIT_COMMAND_MODIFY_GENDER_PROMPT = "7. Edit Gender";
    private static final String WHAT_ELSE_CAN_I_DO_FOR_YOU_TODAY = "What else can I do for you today?";
    private static final String STUDENT_DETAILS_PROMPT = "Enter student details: ";
    private static final String STUDENT_NAME_PROMPT = "Enter student name: ";
    private static final String STUDENT_DETAILS_MESSAGE = "Student details: ";
    private static final String STUDENT_NAME_MESSAGE = "Name: ";
    private static final String CLASSES_ATTENDED_PROMPT = "Enter Classes Attended for said subject (blank to skip): ";
    private static final String CLASSES_ATTENDED_MESSAGE = "Classes Attended: ";
    private static final String STUDENT_NOT_FOUND_MESSAGE = "No student found!";
    private static final String STUDENT_ADDED_MESSAGE = "Student added successfully!";
    private static final String STUDENT_DELETED_MESSAGE = "Student removed successfully!";
    private static final String SUBJECT_MESSAGE = "Subject: ";
    private static final String STUDENT_GRADES_PROMPT = "Current marks out of 100 (blank to skip) : ";
    private static final String STUDENT_GRADES_MESSAGE = "Current marks out of 100: ";
    //@@author alalal47
    private static final String DELETE_UNDONE_MESSAGE = "Last delete undone!";
    private static final String STUDENT_RESTORED_MESSAGE = "Student has been restored!";
    private static final String NO_RECENT_DELETES = "No recent deletes found!";
    private static final String STUDENTS_IN_ARCHIVE = "4. List of students in archive";
    private static final String STUDENTS_IN_DELETED = "5. List of students in recently deleted";
    private static final String ENTER_ONE_TWO_THREE_FOUR = "Enter your choice (1, 2, 3, 4 or 5):";

    //@@ author tayponghee
    private static final String ADD_SUBJECT =
            "Do you want to add another subject and grade? (yes/no)";
    private static final String INVALID_RESPONSE =
            "Invalid response. Please type 'yes' or 'no'.";
    private static final String NO_SUBJECTS_ADDED = "No subjects added.";
    private static final String SUBJECT_ALREADY_EXISTS = "Subject already exists.";
    private static final String WRONG_NUMBER_FORMAT = "Wrong number format! Please try again! e.g. 12 ";
    private static final String TYPE_LIST_ONLY = "Type 'list' only!";
    private static final String SUBJECT_NAME_LEAVE_BLANK = "Enter subject name (leave blank for all students):";
    private static final String STUDENTS_WITH_THE_SUBJECT = "Students with the subject";
    private static final String NO_STUDENTS_FOUND_WITH_THE_SUBJECT = "No students found with the subject: ";
    private static final String CHOOSE_THE_TYPE_OF_LIST_TO_DISPLAY = "Choose the type of list to display: (index only)";
    private static final String FULL_STUDENT_LIST = "1. Full student list";
    private static final String STUDENTS_WITH_TOTAL_CLASSES = "2. List of students with total classes attended";
    private static final String LIST_OF_STUDENTS_WITH_PHONE_NUMBER_SHOWN =
            "3. List of students with phone number shown";
    private static final String STUDENT_NOT_FOUND_CANNOT_BE_DELETED = "Student not found! Cannot be deleted.";
    //@@author alalal47
    /**
     * Displays the help message to teach users how to use Classify.
     */
    public static void printHelp() {
        printAddHelpMessage();
        printEditHelpMessage();
        printViewHelpMessage();
        printDeleteHelpMessage();
        printRestoreHelpMessage();
        printUndoHelpMessage();
        printListHelpMessage();
        printByeHelpMessage();
        printSortHelpMessage();
        printArchiveHelpMessage();
        printUnarchiveHelpMessage();
        printProcessTextFileInputMessage();
        System.out.println("help                        Prints this help message");
    }

    private static void printSortHelpMessage() {
        System.out.println("sort                        Sorts the student list by the input parameter, expects an" +
                " attribute to sort by, can be used directly by sort [type].");
        System.out.println("                            Currently available types: name, classes, payment");
    }

    private static void printRestoreHelpMessage() {
        System.out.println("restore                     Restore a student deleted within the current session, expects" +
                " a name, can be used directly by restore [name].");
    }

    private static void printUndoHelpMessage() {
        System.out.println("undo                        Restores the last student deleted in the current session.");
    }

    private static void printArchiveHelpMessage() {
        System.out.println("archive                     " +
                "Removes the specified student from the list and archives them, can be used directly by archive" +
                " [name].");
    }

    private static void printUnarchiveHelpMessage() {
        System.out.println("unarchive                   " +
                "Removes the specified student from the archive and adds them to the list, can be used directly by" +
                " unarchive [name].");
    }
    //@@ author Cryolian
    private static void printByeHelpMessage() {
        System.out.println("bye                         Exits Classify");
    }

    private static void printListHelpMessage() {
        System.out.println("list                        Displays the list of all students");
        System.out.println("                            Currently available types: Whole student list, with " +
                "with total classes attended, with phone number, ");
        System.out.println("                            the archived list, the recently deleted list or " +
                "by certain subject only.");
    }

    private static void printDeleteHelpMessage() {
        System.out.println("delete                      Deletes a student from the student list, expects a name" +
                ", can be used directly with a name e.g. add [name]");
    }

    private static void printViewHelpMessage() {
        System.out.println("view                        Views a students details, expects a name" +
                ", can be used directly with a name e.g. add [name]");
    }

    private static void printEditHelpMessage() {
        System.out.println("edit                        Edits a students details, expects a name" +
                ", can be used directly with a name e.g. edit [name]");
    }

    private static void printAddHelpMessage() {
        System.out.println("add                         Adds a student to the student list, " +
                "expects a name, grade and lessons attended e.g. add [name]");
    }

    //@@author ParthGandhiNUS
    /**
     * Prints a dividing line between statements for added clarity
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints out the welcome message to introduce the user to Classify
     */
    public static void printWelcomeMessage() {
        printDivider();
        System.out.println(WELCOME_TO_CLASSIFY);
        System.out.println(WHAT_CAN_I_DO_FOR_YOU_TODAY);
    }

    /**
     * Prints out the message to end conversation with the user
     */
    public static void printEndConversation() {
        System.out.println(CLASSIFY_GOODBYE_MESSAGE);
        printDivider();
    }

    public static void printProcessTextFileInputMessage(){
        System.out.println("process                     " +
                "Processes a text file containing a list of students taking the same subject and the same number " +
                "of classes.");
    }

    //@@author blackmirag3
    public static void printSubsequentUserPrompt() {
        System.out.println(WHAT_ELSE_CAN_I_DO_FOR_YOU_TODAY);
        printDivider();
    }
    public static void printWrongInput() {
        System.out.println(WRONG_INPUT_MESSAGE);
        printDivider();
    }

    public static void printStudentDetailsPrompt() {
        System.out.println(STUDENT_DETAILS_PROMPT);
    }

    public static void printStudentNamePrompt() {
        System.out.println(STUDENT_NAME_PROMPT);
    }

    public static void printStudentDetails() {
        System.out.println(STUDENT_DETAILS_MESSAGE);
    }

    /***
     * Print students's details
     * @param student Student instance of whom details to print
     */
    public static void printStudentDetails(Student student) {
        System.out.println(STUDENT_PHONE_MESSAGE + student.getAttributes().getPhoneNumber());
        System.out.println(STUDENT_GENDER_MESSAGE + student.getAttributes().getGender());
        System.out.println(STUDENT_PAYMENT_MESSAGE + student.getAttributes().getLastPaymentDate());
        System.out.println(STUDENT_REMARKS_MESSAGE + student.getAttributes().getRemarks() + "\n");
    }

    public static void printStudentName(String name) {
        System.out.println(STUDENT_NAME_MESSAGE + name + "\n");
    }

    //@@author Cryolian
    public static void printStudentName(Student student) {
        System.out.println(STUDENT_NAME_MESSAGE + student.getName() + "\n");
    }

    //@@author blackmirag3

    /***
     * Print message for successful subject deletion under edit command.
     */
    public static void printSubjectDeleted() {
        System.out.println(EDIT_COMMAND_DELETED_SUBJECT_MESSAGE);
    }

    /***
     * Print prompt for classes attended
     */
    public static void printClassesAttendedPrompt() {
        System.out.println(CLASSES_ATTENDED_PROMPT);
    }

    /***
     * Print attendance count
     * @param attendance int containing attendance count
     */
    public static void printClassesAttended(int attendance) {
        System.out.println(CLASSES_ATTENDED_MESSAGE + attendance);
    }

    /***
     * Print student not found message.
     */
    public static void printStudentNotFound() {
        System.out.println(STUDENT_NOT_FOUND_MESSAGE);
    }

    /***
     * Print student added message.
     */
    public static void printStudentAdded() {
        System.out.println(STUDENT_ADDED_MESSAGE);
    }

    /***
     * Print deleted student message.
     */
    public static void printStudentDeleted() {
        System.out.println(STUDENT_DELETED_MESSAGE);
    }

    /***
     * Prints student's name
     * @param name String containing student's name.
     */
    public static void printSubjectName(String name) {
        System.out.println(SUBJECT_MESSAGE + name);
    }

    /***
     * Prints student grades
     * @param grades Double containing students grade to print.
     */
    public static void printStudentGrades(Double grades) {
        System.out.println(STUDENT_GRADES_MESSAGE + grades);
    }

    /***
     * Prints prompt for user to enter student grade.
     */
    public static void printStudentGradesPrompt() {
        System.out.println(STUDENT_GRADES_PROMPT);
    }

    /***
     * Prints prompts for edit student command.
     */
    public static void printEditPrompt() {
        System.out.println(EDIT_COMMAND_PROMPT);
        System.out.println(EDIT_COMMAND_ADD_SUBJECT_PROMPT);
        System.out.println(EDIT_COMMAND_MODIFY_SUBJECT_PROMPT);
        System.out.println(EDIT_COMMAND_DELETE_SUBJECT_PROMPT);
        System.out.println(EDIT_COMMAND_MODIFY_PHONE_NUMBER_PROMPT);
        System.out.println(EDIT_COMMAND_MODIFY_REMARKS_PROMPT);
        System.out.println(EDIT_COMMAND_MODIFY_PAYMENT_DATE_PROMPT);
        System.out.println(EDIT_COMMAND_MODIFY_GENDER_PROMPT);
    }

    //@@ author tayponghee
    public static void printStudentList(ArrayList<Student> students) {
        System.out.println("List of Students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }
        System.out.println("Currently, there are " + students.size() + " students in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    //@@author Cryolian
    public static void printSameNameError() {
        System.out.println(SAME_NAME_ERROR);
    }

    public static void promptForRemarks() {
        System.out.println(REMARKS_PROMPT);
    }

    public static void promptForLastPaymentDate() {
        System.out.println(PAYMENT_PROMPT);
    }

    public static void promptForGender() {
        System.out.println(GENDER_PROMPT);
    }

    public static void printEmptyNameMessage() {
        System.out.println(EMPTY_NAME_ERROR);
    }

    public static void printPhoneNumberPrompt() {
        System.out.println(PHONE_NUMBER_PROMPT);
        System.out.println("Enter a non-number or blank to exit interface.");
    }
    
    public static void printValidNumberError() {
        System.out.println(VALID_NUMBER_ERROR);
    }

    public static void printEmptyListError() {
        System.out.println(EMPTY_LIST_ERROR);
    }

    public static void printEmptySubjectError() {
        System.out.println(EMPTY_SUBJECT_ERROR);
    }

    public static void printNullAttributeError() {
        System.out.println(NULL_ATTRIBUTE_ERROR);
    }

    public static void printInvalidDateFormatError() {
        System.out.println(INVALID_DATE_ERROR);
    }

    public static void printInvalidDateRangeError() {
        System.out.println(INVALID_DATE_RANGE);
        printDivider();
    }

    //@@author alalal47
    public static void printDeleteUndone() {
        System.out.println(DELETE_UNDONE_MESSAGE);
    }

    public static void printRestoreMessage() {
        System.out.println(STUDENT_RESTORED_MESSAGE);
    }

    public static void printNoDeleteFound() {
        System.out.println(NO_RECENT_DELETES);
    }

    //@@author tayponghee
    public static void printTotalClassesAttended(int classes) {
        System.out.println(TOTAL_CLASSES_ATTENDED_ACROSS_ALL_SUBJECTS + classes);
    }

    public static void println (String text) {
        System.out.println(text);
    }

    public static void printAddSubject() {
        System.out.println(ADD_SUBJECT);
    }

    public static void printInvalidResponse() {
        System.out.println(INVALID_RESPONSE);
    }

    public static void printNoSubjectsAdded() {
        System.out.println(NO_SUBJECTS_ADDED);
    }

    public static void printSubjectAlreadyExists() {
        System.out.println(SUBJECT_ALREADY_EXISTS);
    }

    public static void printWrongNumberFormat() {
        System.out.println(WRONG_NUMBER_FORMAT);
    }

    public static void printInvalidListCommand() {
        System.out.println(TYPE_LIST_ONLY);
    }

    public static void printListCommandStart() {
        System.out.println(SUBJECT_NAME_LEAVE_BLANK);
    }

    public static void printStudentsWithSubject(String subject) {
        System.out.println(STUDENTS_WITH_THE_SUBJECT + " \"" + subject + "\":");
    }

    public static void printNoStudentsWithSubject(String subject) {
        System.out.println(NO_STUDENTS_FOUND_WITH_THE_SUBJECT + subject);
    }

    public static void printStudentToDeleteNotFound() {
        System.out.println(STUDENT_NOT_FOUND_CANNOT_BE_DELETED);
    }

    public static void printListAllStudentsChoice() {
        System.out.println(CHOOSE_THE_TYPE_OF_LIST_TO_DISPLAY);
        System.out.println(FULL_STUDENT_LIST);
        System.out.println(STUDENTS_WITH_TOTAL_CLASSES);
        System.out.println(LIST_OF_STUDENTS_WITH_PHONE_NUMBER_SHOWN);
        System.out.println(STUDENTS_IN_ARCHIVE);
        System.out.println(STUDENTS_IN_DELETED);
        System.out.println(ENTER_ONE_TWO_THREE_FOUR);
    }
}
