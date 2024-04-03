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
        Student foundStudent = InputParsing.findStudentByName(masterStudentList, studentName, in);

        if (foundStudent != null) {
            UI.printStudentDeleted();
        } else {
            UI.printStudentNotFound();
        }

        UI.printDivider();
        //@@author blackmirag3
        if (recentlyDeletedList != null) {
            recentlyDeletedList.add(foundStudent);
        }
        masterStudentList.remove(foundStudent);
        //@@author alalal47
        assert masterStudentList.contains(foundStudent) == false : "student should be removed";
        DataHandler.writeStudentInfo(masterStudentList);

    }

    /**
     * Removes a student from the list.
     * Adds the removed student to a recently deleted list, where the student's information can be recovered
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

        Student foundStudent = InputParsing.findStudentByName(recentlyDeletedList, studentName, in);

        if (foundStudent != null) {
            UI.printRestoreMessage();
        } else {
            UI.printStudentNotFound();
        }

        //@@author Cryolian
        try {
            StudentList.checkNameNumberPair(masterStudentList, studentName, foundStudent.getPhoneNumber());

            //@@author blackmirag3
            UI.printDivider();
            masterStudentList.add(foundStudent);
            recentlyDeletedList.remove(foundStudent);

        //@@author Cryolian
        } catch (NameNumberMatchException e) {
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
        masterStudentList.add(student);
        recentlyDeletedList.remove(student);
        UI.printDeleteUndone();
        UI.printDivider();
    }
}
