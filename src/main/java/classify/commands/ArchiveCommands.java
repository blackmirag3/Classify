package classify.commands;

import classify.data.DataHandler;
import classify.student.Student;
import classify.student.StudentList;
import classify.ui.UI;
import classify.user.InputParsing;
import classify.user.NameNumberMatchException;

import java.util.ArrayList;
import java.util.Scanner;

public class ArchiveCommands {

    //@@author blackmirag3
    /***
     * Prompts for student name to search in archive list
     * If student exists, this method removes student from archive list to add to master list
     * Writes archive and student information file in data directory
     *
     * @param masterList ArrayList of Students who are in master list
     * @param archiveList ArrayList of Students who are archived
     * @param name String for student's name
     * @param in Scanner to read user input
     */
    public static void unarchiveStudent(ArrayList<Student> masterList, ArrayList<Student> archiveList,
                                        String name, Scanner in) {

        Student student = getStudent(archiveList, name, in);

        if (student == null) {
            return;
        }

        //@@author Cryolian
        try {
            StudentList.checkNameNumberPair(masterList, student.getName(), student.getPhoneNumber());
        } catch (NameNumberMatchException e) {
            UI.println("Existing student found with the same name and number");
            UI.printDivider();
            return;
        }
        
        //@@author blackmirag3
        archiveList.remove(student);
        masterList.add(student);
        UI.println("Archive List: ");
        DataHandler.writeArchive(archiveList);
        UI.println("Runtime List: ");
        DataHandler.writeStudentInfo(masterList);
    }

    /***
     * Prompts for student name to search in master student list
     * If student exists, this method removes student from master list to add to archive list
     * Writes archive and student information file in data directory
     *
     * @param masterList ArrayList of Students who are in master list
     * @param archiveList ArrayList of Students who are archived
     * @param name String for student's name
     * @param in Scanner to read user input
     */
    public static void archiveStudent(ArrayList<Student> masterList, ArrayList<Student> archiveList,
                                      String name, Scanner in) {

        Student student = getStudent(masterList, name, in);

        if (student == null) {
            return;
        }

        //@@author Cryolian
        try {
            StudentList.checkNameNumberPair(archiveList, name, student.getPhoneNumber());
        } catch (NameNumberMatchException e) {
            UI.println("Existing student found with the same name and number");
            UI.printDivider();
            return;
        }

        //@@author blackmirag3
        masterList.remove(student);
        archiveList.add(student);
        UI.println("Archive List: ");
        DataHandler.writeArchive(archiveList);
        UI.println("Runtime Database: ");
        DataHandler.writeStudentInfo(masterList);
    }

    private static Student getStudent(ArrayList<Student> archiveList, String name, Scanner in) {
        if (name == null || name.isBlank()) {
            name = Commands.promptName(in);
        }
        Student student = InputParsing.findStudentByName(archiveList, name, in);
        if (student == null) {
            UI.printStudentNotFound();
            return null;
        }
        return student;
    }
}
