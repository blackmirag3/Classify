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
            if (InputParsing.findStudentByName(masterStudentList, studentFileInput) != null) {
                assert InputParsing.findStudentByName(masterStudentList, studentFileInput) != null;
                logger.log(Level.WARNING, "Student with the same name already exists.");

                UI.printSameNameError();
                UI.printDivider();
                return;
            }

            Student student = new Student(studentFileInput);

            masterStudentList.add(student);
            logger.log(Level.INFO, "Student added successfully.");
            UI.printStudentAdded();
            UI.printDivider();
        }
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

