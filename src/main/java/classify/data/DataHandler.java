package classify.data;

import classify.commands.Commands;
import classify.student.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@@author blackmirag3
public class DataHandler extends Commands {
    private static final String DATA_DIRECTORY_PATH = "data/studentInfo";
    private static final String DATA_FILE_PATH = DATA_DIRECTORY_PATH + "/Student_Information.txt";
    private static final String ARCHIVE_DIRECTORY_PATH = "data/archive";
    private static final String ARCHIVE_FILE_PATH = ARCHIVE_DIRECTORY_PATH + "/student_archive.txt";

    /**
     * Function which is called to generate an arrayList "lines" which updates according to the users' inputs.
     * Calls the writeStudentInfoFile function to update Student_Information.txt
     * 
     * @param list ArrayList containing the current students
     */
    public static void writeStudentInfo(List <Student> list){
        DataWriter.writeStudentInfoFile(list, DATA_DIRECTORY_PATH, DATA_FILE_PATH);
    }

    /**
     * This accesses Student_Information.txt and calls the restoreStudentList function
     *
     * @param list ArrayList to read data from txt file into
     * @throws IOException  When unable to get the Student_Information.txt file or has any input errors
     */
    public static void readStudentInfo(ArrayList<Student> list) throws IOException{
        
        System.out.println("Trying to load Student info");
        DataWriter.createParentFileFolder(DATA_DIRECTORY_PATH);
        DataReader.initialiseData(list, DATA_FILE_PATH);
    }

    /**
     * writes student info arraylist into archive file and creates new archive file if one is not found
     *
     * @param list ArrayList containing the current students to write
     */
    public static void writeArchive(List<Student> list){
        DataWriter.writeStudentInfoFile(list, ARCHIVE_DIRECTORY_PATH, ARCHIVE_FILE_PATH);
    }

    /**
     * This accesses archive.txt and fetches information of archived students
     * Updates list input with archived students
     *
     * @param list ArrayList to contain Students from archive.txt
     * @throws IOException  When unable to get the Student_Information.txt file or has any input errors
     */
    public static void readArchive(ArrayList<Student> list) throws IOException {
        System.out.println("Trying to load Student Archive.");
        DataWriter.createParentFileFolder(ARCHIVE_DIRECTORY_PATH);
        DataReader.initialiseData(list, ARCHIVE_FILE_PATH);
    }
}
