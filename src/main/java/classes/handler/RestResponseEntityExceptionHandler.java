package classes.handler;

import classes.data.validation.exception.studentOnPractice.StudentAlreadyOnThisPracticeException;
import classes.data.validation.exception.studentOnPractice.StudentNotOnYourPracticeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { StudentNotOnYourPracticeException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleStudentNotOnYourPractice(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Student not on your practice!";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { StudentAlreadyOnThisPracticeException.class })
    protected ResponseEntity<Object> handleStudentAlreadyOnThisPractice(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Student already on your practice!";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
