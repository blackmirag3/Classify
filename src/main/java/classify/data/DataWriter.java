package classify.data;

import classify.student.Student;
import classify.ui.DataUI;
import classify.ui.UI;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


//@@author ParthGandhiNUS
public class DataWriter {
    private static final String NEWLINE = "\n";
    
    //@@author ParthGandhiNUS
    /**
     * Accesses the parent directory to try to make the parent directory.
     * Tries to write the lines which are made by the writeStudentInfo Method
     * 
     * @param list list ArrayList containing the current students
     */
    public static void writeStudentInfoFile(List <Student> list, String folderPath, String filePath){
        List<String> lines = new ArrayList<>();

        for (Student student:list){
            lines.add(student.textFileInputString());
        }
        DataUI.printAnalysingInput();

        try {
            createParentFileFolder(folderPath);
            FileWriter studentInfoWriter = new FileWriter(filePath);
            for (String line : lines){
                studentInfoWriter.write(line + NEWLINE);
            }
            DataUI.printUpdateSuccess();
            UI.printDivider();
            studentInfoWriter.close();
        } catch (IOException e){
            DataUI.printFileIOError();
        }
    }

    /**
     * Used to create the parent folder for a certain file
     * 
     * @param parentPath    Path file containing the path of folder we want to make the text file in
     * @throws IOException  Triggers whenever the input for the path or creation of the directory is improper
     */
    public static void createParentFileFolder (String parentPath) throws IOException {
        try{
            Path path = Paths.get(parentPath);
            Files.createDirectories(path);
            DataUI.printDirectorySuccess();
        } catch (FileAlreadyExistsException ignored){
            //Ignore this error as this should not cause any issues, we dont want replicas of the same file
        } catch (IOException e){
            DataUI.printCreateFailure();
            throw e;
        }
    }
}
