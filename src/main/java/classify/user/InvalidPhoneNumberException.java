package classify.user;

public class InvalidPhoneNumberException extends Exception{

    public InvalidPhoneNumberException() {
        super();
        System.out.println("Invalid Phone number found.");
    }
    
}
