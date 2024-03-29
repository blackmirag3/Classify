package classify.commands.datacommands;

import classify.data.DataReader;
import classify.data.DataWriter;
import classify.student.Student;
import classify.ui.DataUI;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//@@author blackmirag3
public class ArchiveCommands {
    private static final String ARCHIVE_DIRECTORY_PATH = "data/archive";
    private static final String ARCHIVE_FILE_PATH = ARCHIVE_DIRECTORY_PATH + "/student_archive.txt";

    /**
     * This accesses Student_Information.txt and calls the restoreStudentList function
     *
     * @throws IOException  When unable to get the Student_Information.txt file or has any input errors
     */
    public void readArchive() throws IOException {
        try{
            DataWriter.createParentFileFolder(ARCHIVE_DIRECTORY_PATH);
            DataReader.initialiseData(ARCHIVE_DIRECTORY_PATH, ARCHIVE_FILE_PATH);
        } catch (IOException e) {
            DataUI.printCreateFailure();
            throw e;
        }
    }

    /**
     * writes student info arraylist into archive file and creates new archive file if one is not found
     *
     * @param list ArrayList containing the current students to write
     */
    public static void writeArchive(List<Student> list){
        DataWriter.writeStudentInfoFile(list, ARCHIVE_DIRECTORY_PATH, ARCHIVE_FILE_PATH);
    }
}
