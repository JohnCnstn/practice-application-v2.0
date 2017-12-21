package classes.data.service;

import classes.data.dto.StudentDto;
import classes.data.entity.Practice;
import classes.data.entity.Student;
import classes.data.validation.exception.practice.NumberOfStudentsEqualsQuantity;
import classes.data.validation.exception.signUp.EmailExistsException;
import classes.data.validation.exception.signUp.UserNameExistsException;
import classes.data.validation.exception.studentOnPractice.StudentAlreadyOnThisPracticeException;

import java.util.List;

public interface StudentService {
    Student findOne(long id);
    Student getByFirstName(String firstName);
    Student getByUserName(String studentName);
    void setStudentsOnPractice(List<Long> practicesIds, Long[] id) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity;
    Student deleteStudentFromPractice(StudentDto studentDto);
    List getStudentPractices(long id);
    Student registerNewUserAccount(StudentDto accountDto) throws UserNameExistsException, EmailExistsException;
    Student registerStudent(StudentDto studentDto);
    Student changeStatus(Student student);
    void delete(long id);
    List<Student> getAll();
}
