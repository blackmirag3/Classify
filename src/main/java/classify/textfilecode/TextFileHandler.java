package classify.textfilecode;

import classify.student.Student;
import classify.ui.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
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
    private static final String INVALID_PATH_MESSAGE = "Path is invalid!";
    private static final String NOT_TEXT_FILE_MESSAGE = "Files in your inputFolder are not Text Files!";
    private static final String ADD_TEXT_FILE_MESSAGE = "Add some text files and try processing again!";

    /**
     * Function runs the whole process command, from every input all the way to the final output
     * 
     * @param masterStudentList     StudentList where students will be added
     * @param in                    Scanner which scans for the users' inputs
     */
    public static void process(ArrayList<Student> masterStudentList, Scanner in) {
        createTextFileDirectory(INPUT_TEXT_FILE_DIRECTORY);
        // Print out the current files in your folder
        UI.println(CURRENT_FILES);
        TextFileReader.printCurrentInputFolder(INPUT_TEXT_FILE_DIRECTORY);

        File currentDirectory =new File (INPUT_TEXT_FILE_DIRECTORY);
        File[] fileList = currentDirectory.listFiles();
        assert fileList != null;
        if (fileList.length == 0) {
            return;
        }

        boolean isTextFilePresent = false;
        for (File file : fileList){
            if (file.isFile() && TextFileParser.isTextFile(file.getName())) {
                isTextFilePresent = true;
            }
        }

        if (isTextFilePresent){
            TextFileParser.promptForFileSelection();
            TextFileParser.parseUserSelection(CURRENT_DIRECTORY, in, masterStudentList);
        } else {
            System.out.println(NOT_TEXT_FILE_MESSAGE);
            System.out.println(ADD_TEXT_FILE_MESSAGE);
        }
    }

    /**
     * Used to create the parent folder for a text file for Inputting
     */
    public static void createTextFileDirectory(String pathString) {
        //Folder Creation if folder not there
        try {
            Path path = Paths.get(pathString);
            Files.createDirectories(path);
        } catch (FileAlreadyExistsException ignored) {
            //Ignore this error as this should not cause any issues, we don't want replicas of the same file
        } catch (IOException e) {
            UI.println(IO_EXCEPTION_MESSAGE_FOR_TEXT_FILE);
        } catch (InvalidPathException e) {
            UI.println(INVALID_PATH_MESSAGE);
        }
    }
}
