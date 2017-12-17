package classes.data.validation.exception.signUp;

public class EmailExistsException extends Throwable {
    public EmailExistsException(final String message) {
        super(message);
    }
}
