package classify.data;

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
    private static final Integer ZERO = 0;
    private static final Integer ONE = 1;
    private static final Integer TWO = 2;
    private static final Integer THREE = 3;
    private static final Integer FOUR = 4;
    private static final Integer FIVE = 5;
    private static final Logger logger = Logger.getLogger(InputParsing.class.getName());

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

        if (studentFileInput != null){

            String [] inputArr = studentFileInput.split(MAIN_REGEX);
            
            //Set Name
            Student student = new Student(inputArr[ZERO].trim());

            //Set Gender
            student.getAttributes().setGender(inputArr[ONE].trim());
            
            //Set Phone Number
            try {

                InputParsing.checkForSpecialCharacters(student.getName());

                student.getAttributes().setGender(inputArr[ONE].trim());
                InputParsing.checkForSpecialCharacters(student.getGender());

                int phoneNumber = Integer.parseInt(inputArr[TWO].trim());
                StudentList.checkNameNumberPair(StudentList.masterStudentList, student.getName(), phoneNumber);
                StudentList.checkNameNumberPair(StudentList.archiveList, student.getName(), phoneNumber);
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
                LocalDate inputLastPaymentDate = convertStringInput(inputArr[THREE].trim());
                student.getAttributes().setLastPaymentDate(inputLastPaymentDate);
            } catch (DateTimeParseException e) {
                DataUI.lastPaymentDateParseExceptionMessage();
            }

            //Set remarks
            student.getAttributes().setRemarks(inputArr[FOUR].trim());

            //Get all Subject Info
            try {
                String [] allSubjects = (inputArr[FIVE].trim()).split(SUBJECT_REGEX);
                int numberOfSubjects = allSubjects.length;

                if (numberOfSubjects == 1 && allSubjects[0].isEmpty()){
                    DataUI.noSubjectMessage();
                } else {

                for (int i = 0; i < numberOfSubjects ; i++){
                    String [] subjectDetailedInfo = allSubjects[i].split(SUBJECT_INFO_REGEX);
                    //Subject Name
                    String subjectName = subjectDetailedInfo[ZERO].trim();
                    InputParsing.checkForSpecialCharacters(subjectName);
                    //Subject Grade
                    double subjectGrades = Double.parseDouble(subjectDetailedInfo[ONE].trim());
                    //Classes attended for this subject
                    int subjectClassesAttended = Integer.parseInt(subjectDetailedInfo[TWO].trim());

                        //Add Subject and attributes to the student
                        SubjectGrade newSubject = new SubjectGrade(subjectName, subjectGrades, subjectClassesAttended);
                        student.getAttributes().addSubjectGrade(newSubject);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.println("Error reading in subjects.");
            } catch (InvalidCharacterException e) {
                UI.println("Invalid character found in subject. Skipping subsequent entries.");
            }

            masterStudentList.add(student);
            logger.log(Level.INFO, "Student added successfully.");
            UI.printStudentAdded();
            UI.printDivider();
        }
    }

    public static LocalDate convertStringInput(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateToReturn = LocalDate.parse(input,formatter);
        return dateToReturn;
    }
  
    //@@author blackmirag3
    /**
     * Initialises student array list from specified data file.
     * Creates new data file if data file not found
     *
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
            while (line.ready()) {
                restoreStudentList(line.readLine(), list);
            }
            line.close();

            DataUI.printLoadSuccess();
            UI.printDivider();
        }
    }
}

