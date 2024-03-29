package classify.commands.datacommands;

import classify.data.DataReader;
import classify.data.DataWriter;
import classify.student.Student;
import classify.ui.DataUI;

import java.io.IOException;
import java.util.List;

//@@author ParthGandhiNUS
public class DataCommands {
    private static final String DATA_DIRECTORY_PATH = "data/studentInfo";
    private static final String DATA_FILE_PATH = DATA_DIRECTORY_PATH + "/Student_Information.txt";

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
     * @throws IOException  When unable to get the Student_Information.txt file or has any input errors
     */
    public static void readStudentInfo() throws IOException{
        try{
            DataWriter.createParentFileFolder(DATA_DIRECTORY_PATH);
            DataReader.initialiseData(DATA_DIRECTORY_PATH, DATA_FILE_PATH);
        } catch (IOException e) {
            DataUI.printCreateFailure();
            throw e;
        }
    }
}
