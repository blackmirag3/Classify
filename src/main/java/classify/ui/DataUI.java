package classify.ui;

//@@author blackmirag3
public class DataUI extends UI {
    //@@author ParthGandhiNUS
    private static final String ANALYSING_INPUT_MESSAGE = "Analysing Inputs...";
    private static final String CREATING_DIRECTORY_MESSAGE = "Creating a new directory...";
    private static final String CREATING_FILE_MESSAGE = "Creating a new data file...";
    private static final String DIRECTORY_SUCCESS_MESSAGE = "Directory loaded successfully!";
    private static final String CREATE_FAILURE_MESSAGE = "Unable to create a new file/directory!";
    private static final String RETRIEVING_DATA_MESSAGE = "Retrieving data...";
    private static final String LOAD_DATA_SUCCESS_MESSAGE = "Data loaded sucessfully!";
    private static final String UPDATE_SUCCESS_MESSAGE = "Data update success!";
    private static final String FILE_IO_ERROR_MESSAGE = "File IO Error.";
    private static final String PHONE_NUMBER_PARSE_ERROR = "Error parsing the phone number.";
    private static final String NAME_NUMBER_PAIR = "Existing name and number pair found";
    private static final String INVALID_CHARACTER ="Skipping invalid save entry.";
    private static final String LAST_PAYMENT_DATE_ERROR ="Error parsing the last payment date.";
    private static final String NO_SUBJECT_DATA ="Student does not have any subjects currently!";
    private static final String INDEX_OUT_OF_BOUNDS = "Error reading in subjects data.";

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

    public static void printCreatingFile() {
        System.out.println(CREATING_FILE_MESSAGE);
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

    //@@author ParthGandhiNUS
    public static void phoneNumberParsingError() {
        System.out.println(PHONE_NUMBER_PARSE_ERROR);
    }

    public static void nameNumberPair() {
        System.out.println(NAME_NUMBER_PAIR);
    }
    
    public static void invalidCharacterExceptionMessage() {
        System.out.println(INVALID_CHARACTER);
    }

    public static void lastPaymentDateParseExceptionMessage() {
        System.out.println(LAST_PAYMENT_DATE_ERROR);
    }

    public static void noSubjectMessage() {
        System.out.println(NO_SUBJECT_DATA);
    }

    public static void arrayIndexOutOfBoundsMessage() {
        System.out.println(INDEX_OUT_OF_BOUNDS);
    }
}
