package classify.textfilecode;

import java.io.File;
//@@author ParthGandhiNUS
public class TextFileReader {
    /**
     * Extracted method to convert Path from String to File form.
     * Also calls the get currentFiles method.
     * 
     * @param pathnameString String contaning the filepath
     */
    public static void printCurrentInputFolder(String pathnameString) {
        File currentDirectory = new File (pathnameString);
        getCurrentFiles(currentDirectory);
    }  

    /**
     * Prints out all the files in the path of currentDirectory
     * 
     * @param currentDirectory File where we are querying for the files
     */
    private static void getCurrentFiles(File currentDirectory) {
        File[] fileList = currentDirectory.listFiles();

        if (fileList.length == 0 || fileList == null) {
            System.out.println("No files in your Input Folder!\n" + 
                    "Please add some new files in the correct format!");
            return;
        }

        assert fileList != null;
        
        Integer index = 1;
        for (File newFile : fileList) {
            if (newFile.isFile() && TextFileParser.isTextFile(newFile.getName())) {
                System.out.println(index +". " + newFile.getName());
                index++;
            }
        }
        System.out.println("");
        
    }

}
