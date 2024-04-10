package classify.textfilecode;

import classify.commands.AddStudent;
import classify.student.Student;
import classify.student.SubjectGrade;
import classify.ui.UI;
import classify.user.InputParsing;
import classify.user.InvalidCharacterException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//@@author ParthGandhiNUS
public class TextFileParser {
    private static final String MAIN_REGEX = "~~";
    private static final String FILE_SELECTION_PROMPT = "Please enter the exact name of the file " +
            "you'd like to process:";
    private static final String REQUEST_TO_TRY_AGAIN = "Please try again!";
    private static final String TEXT_TYPE = "txt";
    private static final Character DOT = '.';
    private static final String NO_MATCH_FOUND = "No Match Found!";
    private static final String ERROR_ACCESSING_THE_FILE = "Something went wrong while accessing the file";
    private static final Integer NAME = 0;
    private static final Integer GRADE_FOR_SUBJECT = 1;
    private static final String INPUT_TEXT_FILE_DIRECTORY = "./data/inputFolder";
    private static final String INVALID_CHAR_MESSAGE = "Invalid character found.";
    private static final String INVALID_NUMBER_MESSAGE = "Invalid Number format found.";
    private static final String SUBJECT_REGEX = "Subject:";
    private static final String CLASSES_ATTENDED_REGEX = "Classes Attended:";

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

    /**
     * Take in users' input for the file they want to process and enter parseTextFile Method.
     * If file is not found, user is prompted for other files.
     *
     * @param currentDirectory Directory containing the contents inside inputFolder
     * @param in Scanner to scan users' inputs
     * @param masterStudentList StudentList where students will be added.
     */
    public static void parseUserSelection(File currentDirectory, Scanner in, ArrayList<Student> masterStudentList){
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

    /**
     * Selects data from the correct file and starts reading it.
     *
     * @param fileList List of all the text files in the inputFolder directory
     * @param matchIndex index of the matching file in the list of Files
     * @param masterStudentList StudentList where students will be added.
     */
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

    /**
     * Parse the students' subject name and classes attended before going over all the students taking this class
     *
     * @param line Every line of the file selected by user
     * @param masterStudentList StudentList where students will be added.
     */
    private static void fetchDataFromTextFile(BufferedReader line, ArrayList <Student> masterStudentList) {
        try{
            String firstLine = line.readLine();
            String [] subjectInfoStrings = firstLine.split(SUBJECT_REGEX);
            String subjectName = subjectInfoStrings[1].trim();

            String secondLine = line.readLine();
            String [] classInfoStrings = secondLine.split(CLASSES_ATTENDED_REGEX);
            int totalClassesAttended = Integer.parseInt(classInfoStrings[1].trim());

            while (line.ready()){
                String input = line.readLine();
                deconstructTextFile(input, subjectName, totalClassesAttended, masterStudentList);
            }
        } catch (IOException e) {
            UI.println(ERROR_ACCESSING_THE_FILE);
        }
    }

    /**
     * Adds the new students into the student list and adds their subject, grade, and classes attended.
     * Default value is used for gender, phone number, remarks.
     *
     * @param textFileInput Every line of the text file with the students' information
     * @param subjectName Common subject of the students
     * @param classesAttended Common number of classes attended
     * @param masterStudentList StudentList where students will be added.
     */
    private static void deconstructTextFile (String textFileInput, String subjectName,
                                        Integer classesAttended, ArrayList <Student> masterStudentList){
        if (textFileInput.trim().isBlank()){
            return;
        }
        String [] inputArr = textFileInput.split(MAIN_REGEX);


        try{
            //Set Name
            Student student = new Student(AddStudent.splitName(inputArr[NAME].trim()));
            InputParsing.checkForSpecialCharacters(student.getName());
            //Set grades
            double subjectGrades = Double.parseDouble(inputArr[GRADE_FOR_SUBJECT].trim());
            //Put Everything together
            SubjectGrade newSubject = new SubjectGrade(subjectName, subjectGrades, classesAttended);
            masterStudentList.add(student);
            student.getAttributes().addSubjectGrade(newSubject);
        } catch (InvalidCharacterException e) {
            UI.println(INVALID_CHAR_MESSAGE);
        } catch (NumberFormatException e) {
            UI.println(INVALID_NUMBER_MESSAGE);
        }
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
