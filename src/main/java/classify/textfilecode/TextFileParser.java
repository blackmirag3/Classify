package classify.textfilecode;

import java.io.File;
import java.util.Scanner;

//@@author ParthGandhiNUS
public class TextFileParser {
    private static final String FILESELECTIONPROMPT = "Please enter the exact name of the file you'd like to process:";
    
    /**
     * Boolean that checks for the filetype of the files.
     * Returns true if filetype is .txt, false otherwise. 
     * 
     * @param textFileName Name of the file
     * @return True or False depending on the filetype of file
     */
    public static Boolean textFileChecker(String textFileName){
        String type = "";
        int i = textFileName.lastIndexOf('.');
        if (i >= 0) { 
            type = textFileName.substring(i+1); 
        }
        
        if (type.equals("txt")) {
            return true;
        }
        return false;
    }

    /**
     * Basically prints the prompt for the user to select valid file
     */
    public static void promptForFileSelection(){
        System.out.println(FILESELECTIONPROMPT);
    }

    public static void promptForFileSelectionAgain(){
        System.out.println("Please try again!");
        System.out.println(FILESELECTIONPROMPT);
    }

    public static void parseUserSelection(File currentDirectory,Scanner in){
        File[] fileList = currentDirectory.listFiles();        
        String userinput = in.nextLine();

        int matchIndex = findMatchingFile(fileList, userinput);

        if (matchIndex == -1){
            System.out.println("No Match Found!");
            promptForFileSelectionAgain();
            parseUserSelection(TextFileHandler.currentDirectory, in);
            return;
        }
        
        //Basically now need to parse the file selected
                
    }

    private static Integer findMatchingFile(File[] filelist, String userInput) {
        String input = userInput.toLowerCase().trim();
        for (int i = 0; i < filelist.length; i++){
            String fileName = filelist[i].getName().toLowerCase().trim();
            int dotIndex = fileName.lastIndexOf('.');
            String fileNameWithoutType = fileName.substring(0,dotIndex);
            
            // Return match with the type
            if (input.equals(fileName)){
                return i;
            }

            // Return match without the type
            if (input.equals(fileNameWithoutType)){
                return i;
            }
        }
        return -1;
    }
}
