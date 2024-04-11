package classify.textfilecode;

import classify.student.Student;
import classify.ui.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//@@author ParthGandhiNUS
public class TextFileHandler {
    private static final String CURRENT_FILES = "Current Files in your Input Folder:";
    private static final String INPUT_TEXT_FILE_DIRECTORY = "./data/inputFolder";
    private static final String IO_EXCEPTION_MESSAGE_FOR_TEXT_FILE = "Something went horribly wrong while creating " +
            "your data folder!";
    public static final File CURRENT_DIRECTORY = new File (INPUT_TEXT_FILE_DIRECTORY);

    public static void process(ArrayList<Student> masterStudentList, Scanner in) {
        createTextFileDirectory();
        // Print out the current files in your folder
        UI.println(CURRENT_FILES);
        TextFileReader.printCurrentInputFolder(INPUT_TEXT_FILE_DIRECTORY);
        TextFileParser.promptForFileSelection();
        TextFileParser.parseUserSelection(CURRENT_DIRECTORY, in, masterStudentList);
    }

    /**
     * Used to create the parent folder for a text file for Inputting
     */
    public static void createTextFileDirectory(){
        //Folder Creation if folder not there
        try{
            Path path = Paths.get(INPUT_TEXT_FILE_DIRECTORY);
            Files.createDirectories(path);
        } catch (FileAlreadyExistsException ignored){
            //Ignore this error as this should not cause any issues, we don't want replicas of the same file
        } catch (IOException e){
            UI.println(IO_EXCEPTION_MESSAGE_FOR_TEXT_FILE);
        }
    }
}
