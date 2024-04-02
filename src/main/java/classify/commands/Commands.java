package classify.commands;

import classify.ui.UI;

import java.util.Scanner;

//@@author blackmirag3
public class Commands {
    /***
     * prompts for student name from user input and trims string
     *
     * @param in String containing student's name
     * @return
     */
    public static String promptName(Scanner in) {
        UI.printStudentNamePrompt();
        return in.nextLine().trim();
    }
}
