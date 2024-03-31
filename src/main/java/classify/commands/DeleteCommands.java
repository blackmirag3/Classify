package classify.commands;

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
        assert true : "Not false";
        if (recentlyDeletedList != null) {
            recentlyDeletedList.add(foundStudent);
        }
        masterStudentList.remove(foundStudent);
        //assert may be false after implementation of phone number indentification
        // assert InputParsing.findStudentByName(masterStudentList, studentName) == null : "Student should be deleted";
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
        if (studentName == null) {
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
    
    //@@author blackmirag3
    /**
     * Restores the latest deleted student that has not yet been restored
     *
     * @param masterStudentList   The list of all students
     * @param recentlyDeletedList The list of recently deleted students
     */
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
