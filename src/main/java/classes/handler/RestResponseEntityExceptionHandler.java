package classes.handler;

import classes.data.validation.exception.*;
import classes.data.validation.exception.practice.NumberOfStudentsEqualsQuantity;
import classes.data.validation.exception.studentOnPractice.StudentAlreadyOnThisPracticeException;
import classes.data.validation.exception.studentOnPractice.StudentNotOnYourPracticeException;
import com.sun.media.sound.InvalidDataException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { HeadMasterHaventPractice.class, CustomInvalidDataException.class,
            SpecialityAlreadyExists.class, FacultyAlreadyExists.class, UniversityAlreadyExists.class,
            NumberOfStudentsEqualsQuantity.class, HeadMasterAlreadyHavePractice.class,
            StudentAlreadyOnThisPracticeException.class, StudentNotOnYourPracticeException.class,
            IllegalStateException.class })
    protected ResponseEntity<Object> handleStudentNotOnYourPractice(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getCause().getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
