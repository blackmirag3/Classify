package classify.ui;

//@@author blackmirag3
public class DataUI extends UI {
    //@@author ParthGandhiNUS
    private static final String ANALYSING_INPUT_MESSAGE = "Analysing Inputs...";
    private static final String CREATING_DIRECTORY_MESSAGE = "Creating a new directory...";
    private static final String DIRECTORY_SUCCESS_MESSAGE = "Directory loaded successfully!";
    private static final String CREATE_FAILURE_MESSAGE = "Unable to create a new file/directory!";
    private static final String RETRIEVING_DATA_MESSAGE = "Retrieving data...";
    private static final String LOAD_DATA_SUCCESS_MESSAGE = "Data loaded sucessfully!";
    private static final String UPDATE_SUCCESS_MESSAGE = "Data update success!";
    private static final String FILE_IO_ERROR_MESSAGE = "File IO Error.";

    //@@author blackmirag3
    public static void printLoadSuccess() {
        System.out.println(LOAD_DATA_SUCCESS_MESSAGE);
    }
    public static void printFileIOError() {
        System.out.println(FILE_IO_ERROR_MESSAGE);
    }
    public static void printUpdateSuccess() {
        System.out.println(UPDATE_SUCCESS_MESSAGE);
    }
    public static void printAnalysingInput() {
        System.out.println(ANALYSING_INPUT_MESSAGE);
    }

    public static void printCreatingDirectory() {
        System.out.println(CREATING_DIRECTORY_MESSAGE);
    }

    public static void printDirectorySuccess() {
        System.out.println(DIRECTORY_SUCCESS_MESSAGE);
    }

    public static void printCreateFailure() {
        System.out.println(CREATE_FAILURE_MESSAGE);
    }

    public static void printRetrieveData() {
        System.out.println(RETRIEVING_DATA_MESSAGE);
    }
}
