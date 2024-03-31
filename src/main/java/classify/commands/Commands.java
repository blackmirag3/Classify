package classify.commands;

import classify.ui.UI;

import java.util.Scanner;

//@@author blackmirag3
public class Commands {
    public static String promptName(Scanner in) {
        UI.printStudentNamePrompt();
        return in.nextLine().trim();
    }
}
