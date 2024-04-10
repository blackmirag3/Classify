package classify.commands;

import classify.data.DataHandler;
import classify.student.Student;
import classify.student.StudentList;
import classify.user.InputParsing;
import classify.user.NameNumberMatchException;
import classify.ui.UI;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteCommands extends Commands {
    //@@author alalal47
    /**
     * Removes a student from the list.
     * Adds the removed student to a recently deleted list, where the student's information can be recovered
     *
     * @param masterStudentList   The list of all students
     * @param recentlyDeletedList The list of recently deleted students
     * @param in                  The scanner object to read user input
     * @param studentName         The name of the student if the user had entered it
     *                            before being prompted
     */
    public static void deleteStudent(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList,
                                     Scanner in, String studentName) {
        //@@author blackmirag3
        if (studentName == null) {
            studentName = promptName(in);
        }

        //@@author alalal47
        Student student = InputParsing.findStudentByName(masterStudentList, studentName, in);

        if (student != null) {
            UI.printStudentDeleted();
        } else {
            UI.printStudentNotFound();
            UI.printDivider();
            return;
        }

        UI.printDivider();
        //@@author blackmirag3
        if (recentlyDeletedList != null) {
            recentlyDeletedList.add(student);
        }
        masterStudentList.remove(student);
        //@@author alalal47
        assert masterStudentList.contains(student) == false : "student should be removed";
        DataHandler.writeStudentInfo(masterStudentList);

    }

    /**
     * Restores a student from the list.
     * Adds the removed student back from the recently deleted list to the masterlist.
     *
     * @param masterStudentList   The list of all students.
     * @param recentlyDeletedList The list of recently deleted students
     * @param in                  The scanner object to read user input.
     * @param studentName         The name of the student if the user had entered it
     *                            before being prompted
     */
    public static void restoreStudent(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList,
                                      Scanner in, String studentName) {
        //@@author blackmirag3
        if (studentName == null || studentName.isBlank()) {
            studentName = promptName(in);
        }

        Student student = InputParsing.findStudentByName(recentlyDeletedList, studentName, in);

        if (student != null) {
            UI.printRestoreMessage();
        } else {
            UI.printStudentNotFound();
        }

        //@@author Cryolian
        try {
            StudentList.checkNameNumberPair(StudentList.masterStudentList, student.name, student.getPhoneNumber());
            StudentList.checkNameNumberPair(StudentList.archiveList, student.name, student.getPhoneNumber());

            //@@author blackmirag3
            UI.printDivider();
            masterStudentList.add(student);
            recentlyDeletedList.remove(student);
            DataHandler.writeStudentInfo();

        //@@author Cryolian
        } catch (NameNumberMatchException e) {
            UI.printDivider();
            return;
        } catch (NullPointerException e) {
            UI.printDivider();
            return;
        }

    }
    
    //@@author alalal47
    /**
     * Restores the latest deleted student that has not yet been restored
     *
     * @param masterStudentList   The list of all students
     * @param recentlyDeletedList The list of recently deleted students
     */
    //@@author alalal47
    public static void undoDelete(ArrayList<Student> masterStudentList, ArrayList<Student> recentlyDeletedList) {
        if (recentlyDeletedList.isEmpty()) {
            UI.printNoDeleteFound();
            UI.printDivider();
            return;
        }
        Student student = recentlyDeletedList.get(recentlyDeletedList.size() - 1);

        try {
            StudentList.checkNameNumberPair(StudentList.masterStudentList, student.getName(), 
                    student.getPhoneNumber());

            StudentList.checkNameNumberPair(StudentList.archiveList, student.getName(), student.getPhoneNumber());
        } catch (NameNumberMatchException e) {
            UI.println("Name number match found in archive or master list.");
            UI.println("Save file may have been tampered with.");
            UI.println("Removing student from recently deleted list.");
            recentlyDeletedList.remove(student);
            UI.printDivider();
            return;
        } 

        masterStudentList.add(student);
        recentlyDeletedList.remove(student);
        UI.printDeleteUndone();
        UI.printDivider();
    }
}
