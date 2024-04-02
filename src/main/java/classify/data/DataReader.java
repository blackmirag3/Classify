package classify.data;

import classify.student.Student;
import classify.ui.DataUI;
import classify.user.InputParsing;
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
    private static final Logger logger = Logger.getLogger(InputParsing.class.getName());

    public static Scanner in = new Scanner(System.in);


    //@@author ParthGandhiNUS
    /**
     * Method restores the previous student list by accessing the
     * line-by-line information in the Student_Information.txt
     *
     * @param studentFileInput  Line-by-line information of strings in the Student_Information.txt
     * @param masterStudentList The list of all students
     */
    public static void restoreStudentList(String studentFileInput, ArrayList <Student> masterStudentList ){

        if (studentFileInput != null){

            String [] inputArr = studentFileInput.split("~~");
            
            //Set Name
            Student student = new Student(inputArr[0].trim());
            masterStudentList.add(student);

            //Set Gender
            student.getAttributes().setGender(inputArr[1].trim());
            
            //Set Phone Number
            try {
            student.getAttributes().setPhoneNumber(Integer.parseInt(inputArr[2].trim()));
            } catch (NumberFormatException e) {
                UI.println("Error parsing the phone number.");
            }

            //Set Last Payment Date
            try {
            LocalDate inputLastPaymentDate = convertStringInput(inputArr[3].trim());
            student.getAttributes().setLastPaymentDate(inputLastPaymentDate);
            } catch (DateTimeParseException e) {
                UI.println("Error parsing the last payment date.");
            }

            //Set remarks
            student.getAttributes().setRemarks(inputArr[4].trim());
            
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

    public static void initialiseData(ArrayList<Student> list,
                                      String filePath) throws IOException {
        //@@author blackmirag3
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

