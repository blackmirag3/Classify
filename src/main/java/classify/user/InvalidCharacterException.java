package classify.user;

public class InvalidCharacterException extends Exception {

    public InvalidCharacterException() {
        super();
        System.out.println("Special character found. Exiting current interface.");
    }
    
}
