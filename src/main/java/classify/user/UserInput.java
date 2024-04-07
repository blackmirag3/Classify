package classify.user;

public class UserInput {
    //@@author alalal47
    /**
     * Used to process the different variations of the users inputs
     * @param userInput : String which the user inputs
     * @return String[] which contains the command in [0] and name in [1] if the user chose to input it
     */
    public static String[] processInput(String userInput) {

        String[] commandWithName = new String[2];

        try {
            if  (userInput.trim().contains(" ")) {
                commandWithName = userInput.split(" ", 2);
                
                InputParsing.checkForSpecialCharacters(commandWithName[1]);

            } else {
                commandWithName[0] = userInput.trim();
            }
        } catch (InvalidCharacterException e) {
            return null;
        }

        commandWithName[0] = commandWithName[0].toLowerCase();
        return commandWithName;
    }

}
