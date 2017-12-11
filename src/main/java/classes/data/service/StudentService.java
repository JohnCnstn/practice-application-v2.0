package classes.data.service;

import classes.data.dto.StudentDto;
import classes.data.entity.Student;
import classes.data.validation.exception.EmailExistsException;
import classes.data.validation.exception.UserNameExistsException;

import java.util.List;

public interface StudentService {
    Student findOne(long id);
    Student getByFirstName(String firstName);
    Student getByUserName(String studentName);
    Student setStudentOnPractice(StudentDto studentDto);
    Student deleteStudentFromPractice(StudentDto studentDto);
    List getStudentPractices(long id);
    Student registerNewUserAccount(StudentDto accountDto) throws UserNameExistsException, EmailExistsException;
    Student registerStudent(StudentDto studentDto);
    Student changeStatus(Student student);
    void delete(long id);
    List<Student> getAll();
}
