package classify.data;

import classify.commands.AddStudent;
import classify.student.Student;
import classify.student.StudentList;
import classify.student.SubjectGrade;
import classify.ui.DataUI;
import classify.user.InputParsing;
import classify.user.InvalidCharacterException;
import classify.user.NameNumberMatchException;
import classify.ui.UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author ParthGandhiNUS
public class DataReader {
    private static final String MAIN_REGEX = "~~";
    private static final String SUBJECT_REGEX = "#--#";
    private static final String SUBJECT_INFO_REGEX = "##";
    private static final Integer NAME = 0;
    private static final Integer SUBJECT_NAME = 0;
    private static final Integer GENDER = 1;
    private static final Integer GRADE_FOR_SUBJECT = 1;
    private static final Integer PHONE_NUMBER = 2;
    private static final Integer NUMBER_OF_CLASSES_ATTENDED = 2;
    private static final Integer LAST_PAYMENT_DATE = 3;
    private static final Integer REMARKS = 4;
    private static final Integer SUBJECTS = 5;
    private static final Logger logger = Logger.getLogger(InputParsing.class.getName());
    private static final String INVALID_CHAR_MESSAGE = "Invalid character found.";
    private static final String INVALID_NUMBER_MESSAGE = "Invalid Number format found.";
    private static final String ARRAY_INDEX_MESSAGE = "Error reading in subjects.";

    public static Scanner in = new Scanner(System.in);

    /**
     * Method restores the previous student list by accessing the
     * line-by-line information in the Student_Information.txt
     * All information about the student is updated.
     *
     * @param studentFileInput  Line-by-line information of strings in the Student_Information.txt
     * @param masterStudentList The list of all students
     */
    public static void restoreStudentList(String studentFileInput, ArrayList <Student> masterStudentList ){

        if (studentFileInput.trim().isBlank()) {
            return;
        }
        String[] inputArr = studentFileInput.split(MAIN_REGEX);

        //Set Name
        Student student = new Student(AddStudent.splitName(inputArr[NAME].trim()));
        //Set Gender
        student.getAttributes().setGender(inputArr[GENDER].trim());
        //Set remarks
        student.getAttributes().setRemarks(inputArr[REMARKS].trim());
        //Set Phone Number
        try {
            int phoneNumber = Integer.parseInt(inputArr[PHONE_NUMBER].trim());

            if (!InputParsing.checkNumberValidity(phoneNumber)) {
                UI.println("Invalid phone number found in save file.");
                UI.println("Skipping entry: " + student.getName());
                return;
            } 
            
            requiredDataChecks(student, phoneNumber);
            student.getAttributes().setPhoneNumber(phoneNumber);

        } catch (NumberFormatException e) {
            DataUI.phoneNumberParsingError();
            return;
        } catch (NameNumberMatchException e) {
            DataUI.nameNumberPair();
            return;
        } catch (InvalidCharacterException e) {
            DataUI.invalidCharacterExceptionMessage();
            return;
        }
        //Set Last Payment Date
        try {
            LocalDate inputLastPaymentDate = convertStringInput(inputArr[LAST_PAYMENT_DATE].trim());
            student.getAttributes().setLastPaymentDate(inputLastPaymentDate);
        } catch (DateTimeParseException e) {
            DataUI.lastPaymentDateParseExceptionMessage();
        }
        //Get all Subject Info
        try {
            addDifferentSubjectData(inputArr, student);
        } catch (ArrayIndexOutOfBoundsException e) {
            UI.println(ARRAY_INDEX_MESSAGE);
        } catch (InvalidCharacterException e) {
            UI.println(INVALID_CHAR_MESSAGE);
        }
        masterStudentList.add(student);
        logger.log(Level.INFO, student.getName() + " added successfully.");
    }

    /**
     * This function does the necessary checks for all the students in the StudentInformation.txt file.
     * It throws exceptions if any of the checks are violated
     *
     * @param student Refers to the student which is to be added to the masterStudentList
     * @param phoneNumber Refers to the phone number stored in text file for this particular student.
     * @throws InvalidCharacterException Thrown when an invalid character is used.
     * @throws NameNumberMatchException Thrown when there are matching phone numbers in archive or master list.
     */
    private static void requiredDataChecks(Student student, int phoneNumber) throws InvalidCharacterException,
            NameNumberMatchException {
        InputParsing.checkForSpecialCharacters(student.getName());
        InputParsing.checkForSpecialCharacters(student.getGender());
        InputParsing.checkForSpecialCharacters(student.getAttributes().getRemarks());
        StudentList.checkNameNumberPair(StudentList.masterStudentList, student.getName(), phoneNumber);
        StudentList.checkNameNumberPair(StudentList.archiveList, student.getName(), phoneNumber);
    }

    /**
     * Parses the data for the different subjects a student is taking and calls getAllSubjectInformation method
     *
     * @param inputArr Refers to the array containing data about the Students' Subjects
     * @param student Refers to the student which is to be added to the masterStudentList
     * @throws InvalidCharacterException Thrown when an invalid character is used.
     */
    private static void addDifferentSubjectData(String[] inputArr, Student student) throws InvalidCharacterException {
        String [] allSubjects = (inputArr[SUBJECTS].trim()).split(SUBJECT_REGEX);
        int numberOfSubjects = allSubjects.length;

        if (numberOfSubjects == 1 && allSubjects[0].isEmpty()){
            DataUI.noSubjectMessage();
            return;
        }

        for (String allSubject : allSubjects) {
            try {
                SubjectGrade newSubject = getAllSubjectInformation(allSubject);
                student.getAttributes().addSubjectGrade(newSubject);
            } catch (InvalidCharacterException e) {
                UI.println(INVALID_CHAR_MESSAGE);
            } catch (NumberFormatException e){
                UI.println(INVALID_NUMBER_MESSAGE);
            }
        }
    }

    /**
     * Takes in the string containing all the info regarding a students' subject
     *
     * @param allSubjects String containing all the subject's information
     * @return all the subject information which needs to be added as a SubjectGrade
     * @throws InvalidCharacterException Exception if the character is invalid
     */
    private static SubjectGrade getAllSubjectInformation(String allSubjects) throws InvalidCharacterException,
            NumberFormatException {
        String [] subjectDetailedInfo = allSubjects.split(SUBJECT_INFO_REGEX);
        //Subject Name
        String subjectName = subjectDetailedInfo[SUBJECT_NAME].trim();
        InputParsing.checkForSpecialCharacters(subjectName);
        //Subject Grade
        double subjectGrades = Double.parseDouble(subjectDetailedInfo[GRADE_FOR_SUBJECT].trim());
        //Classes attended for this subject
        int subjectClassesAttended = Integer.parseInt(subjectDetailedInfo[NUMBER_OF_CLASSES_ATTENDED]
                .trim());

        //Add Subject and attributes to the student
        return new SubjectGrade(subjectName, subjectGrades, subjectClassesAttended);
    }

    /**
     * Simple function to convert the date from String form to LocalDate Form
     *
     * @param input is the data about the date of the last payment in String form
     * @return the data about the date of last payment in the LocalDate form
     * @throws DateTimeParseException if there is some error experienced when trying to convert to LocalDate format
     */
    public static LocalDate convertStringInput(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(input,formatter);
    }
  
    //@@author blackmirag3
    /**
     * Initialises student array list from specified data file.
     * Creates new data file if data file not found
     * If corruption is detected, prompts user to manually rectify it or gives the option to empty the file.
     *
     * @param list ArrayList containing Students
     * @param filePath String containing path to data file
     * @throws IOException Thrown when there is error reading the data file.
     */
    public static void initialiseData(ArrayList<Student> list,
                                      String filePath) throws IOException {

        Path directory = Paths.get(filePath);
        if (!Files.exists(directory)) {
            DataUI.printCreatingFile();
            Files.createFile(directory);
        } else {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader line = new BufferedReader(fileReader);
            DataUI.printRetrieveData();

            //@@author ParthGandhiNUS
            try {
                while (line.ready()) {
                    restoreStudentList(line.readLine(), list);
                }
                line.close();
            //@@author alalal47
            } catch (ArrayIndexOutOfBoundsException e) {
                DataUI.printFileCorruptedMessage();
                DataUI.printDataDeletionPromptMessage();
                promptDataDeletion();
                UI.printDivider();
                StudentList.masterStudentList.clear();
            } catch (IOException e) {
                DataUI.printFileCorruptedMessage();
                DataUI.printDataDeletionPromptMessage();
                promptDataDeletion();
                UI.printDivider();
                StudentList.masterStudentList.clear();
            }
        }
        //@@author ParthGandhiNUS
        DataUI.printLoadSuccess();
        UI.printDivider();
    }


    //@@author alalal47
    /**
     * Function is called in the case of file corruption, and is not localised in a data entry,
     * in a way that is skipped and handled by our other exceptions and checks.
     * Prompts the user to manually fix any errors they may have introduced by manual editing,
     * then to restart Classify.
     * Or, provides users the option to wipe all existing data in the case that they are unable to.
     */
    private static void promptDataDeletion() {
        String input = in.nextLine();
        if (checkDeletionRequestInput(input)) {
            DataUI.printDataDeletionFinalConfirmationMessage();
            input = in.nextLine();
            if (deletionRequestFinalConfirmation(input)) {
                DataHandler.deleteStudentInfo();
            } else {
                DataUI.printDeletionConfirmationInputMismatchMessage();
                promptDataDeletion();
            }
        } else {
            DataUI.printDeletionInputMismatchMessage();
            promptDataDeletion();
        }
    }

    private static boolean checkDeletionRequestInput(String input) {
        return input.equals("YES");
    }

    private static boolean deletionRequestFinalConfirmation(String input) {
        return input.equals("CONFIRM");
    }
}

