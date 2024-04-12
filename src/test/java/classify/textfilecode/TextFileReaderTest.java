package classify.textfilecode;
//@@author ParthGandhiNUS

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class TextFileReaderTest {
    private static final String VALID_FILE_DIRECTORY = "./data/testFolder";

    @Test
    public void testPrintCurrentInputFolderwhenEmpty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TextFileHandler.createTextFileDirectory(VALID_FILE_DIRECTORY);

        TextFileReader.printCurrentInputFolder(VALID_FILE_DIRECTORY);

        assertEquals("No files in your Input Folder!\n" + 
        "Please add some new files in the correct format!", outContent.toString().trim());
    }
    
    @Test
    public void testPrintCurrentInputFolderwhenFilled() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TextFileHandler.createTextFileDirectory(VALID_FILE_DIRECTORY);
        File currentDir = new File (VALID_FILE_DIRECTORY);
        File testFile1 = new File (currentDir, "file1.txt");
        try {
            testFile1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        TextFileReader.printCurrentInputFolder(VALID_FILE_DIRECTORY);

        assertEquals("1. file1.txt", outContent.toString().trim());

        testFile1.delete();
        currentDir.delete();
    }
 }