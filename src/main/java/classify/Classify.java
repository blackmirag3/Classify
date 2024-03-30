package classify;

import classify.commands.FileIOCommands;
import classify.student.StudentList;
import classify.user.InputParsing;
import classify.ui.UI;
import classify.user.UserInput;

import java.io.IOException;
import java.util.Scanner;

public class Classify {
    public static Scanner in = new Scanner(System.in);

    /**
     * Main entry-point for the Classify application.
     * 
     * @param args          Input arguments when running the program.
     * @throws IOException  Thrown when error reading file.
     */
    public static void main(String[] args) throws IOException {

        // @@author ParthGandhiNUS  
        UI.printWelcomeMessage();
        UI.printUserPrompt();
        FileIOCommands.readStudentInfo(StudentList.masterStudentList);
        FileIOCommands.readArchive(StudentList.archiveList);
        
        // Takes in input from the user, and processes input to determine if it contains a command and a name   
        String[] userCommand = UserInput.processInput(in.nextLine());

        // Set up polling for the first word input by the user.
        // If user's first word is "bye", will exit the while loop.
        while (!(userCommand[0].equals("bye"))){
            InputParsing.parseUserCommand(userCommand, StudentList.masterStudentList, StudentList.recentlyDeletedList,
                    StudentList.archiveList, in);
            userCommand = UserInput.processInput(in.nextLine());
        }

        UI.printEndConversation();
    }
}

