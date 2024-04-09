package classify.textfilecode;

import classify.data.DataReader;
import classify.student.Student;
import classify.student.StudentList;
import classify.student.SubjectGrade;
import classify.ui.DataUI;
import classify.ui.UI;
import classify.user.InputParsing;
import classify.user.InvalidCharacterException;
import classify.user.NameNumberMatchException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

//@@author ParthGandhiNUS
public class TextFileParser {
    private static final String MAIN_REGEX = "~~";
    private static final String SUBJECT_INFO_REGEX = "##";
    private static final String FILE_SELECTION_PROMPT = "Please enter the exact name of the file " +
            "you'd like to process:";
    private static final String REQUEST_TO_TRY_AGAIN = "Please try again!";
    private static final String TEXT_TYPE = "txt";
    private static final Character DOT = '.';
    private static final String NO_MATCH_FOUND = "No Match Found!";
    private static final String ERROR_ACCESSING_THE_FILE = "Something went wrong while accessing the file";
    private static final Integer NAME = 0;
    private static final Integer GENDER = 1;
    private static final Integer PHONE_NUMBER = 2;
    private static final Integer LAST_PAYMENT_DATE = 3;
    private static final Integer REMARKS = 4;
    private static final Integer SUBJECTS = 5;
    private static final Integer GRADE_FOR_SUBJECT = 0;
    private static final Integer NUMBER_OF_CLASSES_ATTENDED = 1;
    private static final String INPUT_TEXT_FILE_DIRECTORY = "./data/inputFolder";
    /**
     * Boolean that checks for the filetype of the files.
     * Returns true if filetype is .txt, false otherwise. 
     * 
     * @param textFileName Name of the file
     * @return True or False depending on the filetype of file
     */
    public static Boolean textFileChecker(String textFileName){
        String type = "";
        int i = textFileName.lastIndexOf(DOT);
        if (i >= 0) { 
            type = textFileName.substring(i+1); 
        }

        return type.equals(TEXT_TYPE);
    }

    /**
     * Prints the prompt for the user to select valid file
     */
    public static void promptForFileSelection(){
        System.out.println(FILE_SELECTION_PROMPT);
    }

    /**
     * Prints the prompt for the user to select valid file after entering a wrong input before.
     */
    public static void promptForFileSelectionAgain(){
        System.out.println(REQUEST_TO_TRY_AGAIN);
        System.out.println(FILE_SELECTION_PROMPT);
    }
    
    public static void parseUserSelection(File currentDirectory, Scanner in, ArrayList <Student> masterStudentList){
        File[] fileList = currentDirectory.listFiles();
        String userInput = in.nextLine();

        assert fileList != null;
        int matchIndex = findMatchingFile(fileList, userInput);

        if (matchIndex == -1){
            System.out.println(NO_MATCH_FOUND);
            promptForFileSelectionAgain();
            parseUserSelection(TextFileHandler.CURRENT_DIRECTORY, in, masterStudentList);
            return;
        }
        
        parseTextFile(fileList, matchIndex, masterStudentList);
                
    }

    private static void parseTextFile(File[] fileList, Integer matchIndex, ArrayList <Student> masterStudentList){
        String fileNameString = fileList[matchIndex].getName().trim();
        String fullFilePath = INPUT_TEXT_FILE_DIRECTORY + "/" + fileNameString;
        try {
            FileReader textfileReader = new FileReader(fullFilePath);
            BufferedReader line = new BufferedReader(textfileReader);
            UI.println("Fetching the data from " + fileNameString + ".");
            fetchDataFromTextFile(line, masterStudentList);

        } catch (FileNotFoundException e) {
            UI.println(ERROR_ACCESSING_THE_FILE);
        }

    }

    private static void fetchDataFromTextFile(BufferedReader line, ArrayList <Student> masterStudentList) {
        try{
            String firstLine = line.readLine();
            String [] subjectInfoStrings = firstLine.split("Subject:");
            String subjectName = subjectInfoStrings[1].trim();

            while (line.ready()){
                String input = line.readLine();
                decodeTextFile(input, subjectName, masterStudentList);
            }
        } catch (IOException e) {
            UI.println(ERROR_ACCESSING_THE_FILE);
        }
    }

    private static void decodeTextFile (String textFileInput, String subjectName,
                                        ArrayList <Student> masterStudentList){
        if (textFileInput.trim().isBlank()){
            return;
        }
        String [] inputArr = textFileInput.split(MAIN_REGEX);

        //Set Name
        Student student = new Student(inputArr[NAME].trim());
        masterStudentList.add(student);
        //Set Gender
        student.getAttributes().setGender(inputArr[GENDER].trim());
        //Set Phone Number
        try {

            InputParsing.checkForSpecialCharacters(student.getName());
            InputParsing.checkForSpecialCharacters(student.getGender());
            int phoneNumber = Integer.parseInt(inputArr[PHONE_NUMBER].trim());
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
            LocalDate inputLastPaymentDate = DataReader.convertStringInput(inputArr[LAST_PAYMENT_DATE].trim());
            student.getAttributes().setLastPaymentDate(inputLastPaymentDate);
        } catch (DateTimeParseException e) {
            DataUI.lastPaymentDateParseExceptionMessage();
        }
        //Set remarks
        student.getAttributes().setRemarks(inputArr[REMARKS].trim());
        
        String [] subjectInformation = inputArr[SUBJECTS].trim().split(SUBJECT_INFO_REGEX);
        //Set grades
        double subjectGrades = Double.parseDouble(subjectInformation[GRADE_FOR_SUBJECT].trim());
        //Set Classes Attended
        int subjectClassesAttended = Integer.parseInt(subjectInformation[NUMBER_OF_CLASSES_ATTENDED].trim());
        SubjectGrade newSubject = new SubjectGrade(subjectName, subjectGrades, subjectClassesAttended);
        student.getAttributes().addSubjectGrade(newSubject);
    }

    /**
     * This method returns the index of file which matches the user's input for file to be processed
     * 
     * @param fileList List of files in current directory
     * @param userInput User's input
     * @return Returns the index of the match found or else returns -1.
     */
    private static Integer findMatchingFile(File[] fileList, String userInput) {
        String input = userInput.toLowerCase().trim();
        for (int i = 0; i < fileList.length; i++){
            String fileName = fileList[i].getName().toLowerCase().trim();
            int dotIndex = fileName.lastIndexOf(DOT);
            String fileNameWithoutType = fileName.substring(0,dotIndex);
            
            // Return match with the type
            if (input.equals(fileName)){
                return i;
            }

            // Return match without the type
            if (input.equals(fileNameWithoutType)){
                return i;
            }
        }
        return -1;
    }
}
