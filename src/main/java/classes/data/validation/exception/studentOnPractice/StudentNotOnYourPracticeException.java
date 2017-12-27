package classes.data.validation.exception.studentOnPractice;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class StudentNotOnYourPracticeException extends Throwable {
    public StudentNotOnYourPracticeException() {
    }
}
