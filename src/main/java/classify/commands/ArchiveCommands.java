package classify.commands;

import classify.data.DataHandler;
import classify.student.Student;
import classify.ui.UI;
import classify.user.InputParsing;

import java.util.ArrayList;
import java.util.Scanner;

public class ArchiveCommands {
    //@@author blackmirag3
    public static void unarchiveStudent(ArrayList<Student> masterList, ArrayList<Student> archiveList,
                                        String name, Scanner in) {
        if (name == null) {
            name = Commands.promptName(in);
        }
        Student student = InputParsing.findStudentByName(archiveList, name, in);
        if (student == null) {
            UI.printStudentNotFound();
            return;
        }
        archiveList.remove(student);
        masterList.add(student);
        DataHandler.writeArchive(archiveList);
        DataHandler.writeStudentInfo(masterList);
    }

    public static void archiveStudent(ArrayList<Student> masterList, ArrayList<Student> archiveList,
                                      String name, Scanner in) {
        if (name == null) {
            name = Commands.promptName(in);
        }
        Student student = InputParsing.findStudentByName(masterList, name, in);
        if (student == null) {
            UI.printStudentNotFound();
            return;
        }
        masterList.remove(student);
        archiveList.add(student);
        DataHandler.writeArchive(archiveList);
        DataHandler.writeStudentInfo(masterList);
    }
}
