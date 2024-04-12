package classify.textfilecode;
//@@author ParthGandhiNUS

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TextFileHandlerTest {
    private static final String VALID_FILE_DIRECTORY = "./data/testFolder";

    /**
     * Test checks for the creation of new  directories
     */
    @Test
    public void testCreateTextFileDirectorySuccessfulCreation() {
        TextFileHandler.createTextFileDirectory(VALID_FILE_DIRECTORY);
        File currentDir = new File (VALID_FILE_DIRECTORY);
        Path expectedDirectory = Paths.get(VALID_FILE_DIRECTORY);
        assertTrue( Files.exists(expectedDirectory));
        currentDir.delete();
    }
}
