package classes.data.validation.exception.signUp;

public class UserNameExistsException extends Throwable{
    public UserNameExistsException(final String message) {
        super(message);
    }
}
