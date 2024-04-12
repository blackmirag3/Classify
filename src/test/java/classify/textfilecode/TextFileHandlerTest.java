package classify.textfilecode;
//@@author ParthGandhiNUS

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextFileHandlerTest {
    private static final String VALID_FILE_DIRECTORY = "./data/testFolder";
     private static final String INVALID_FILE_DIRECTORY = "./data/invalid**";
     private static final String INVALID_PATH_MESSAGE = "Path is invalid!";

    /**
     * Test checks for the creation of new  directories
     * @throws IOException 
     */
    @Test
    public void testCreateTextFileDirectory_SuccessfulCreation() {
        TextFileHandler.createTextFileDirectory(VALID_FILE_DIRECTORY);
        File currentDir = new File (VALID_FILE_DIRECTORY);
        Path expectedDirectory = Paths.get(VALID_FILE_DIRECTORY);
        assertTrue( Files.exists(expectedDirectory));
        currentDir.delete();
    }

    @Test
    public void testCreateTextFileDirectory_InvalidPath() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TextFileHandler.createTextFileDirectory(INVALID_FILE_DIRECTORY);

        assertEquals(INVALID_PATH_MESSAGE, outContent.toString().trim());
    }
}
