package classes.data.service;

import classes.data.dto.FacultyDto;
import classes.data.dto.PracticeDto;
import classes.data.dto.StudentDto;
import classes.data.entity.Student;
import classes.data.validation.exception.EmailExistsException;
import classes.data.validation.exception.UserNameExistsException;

import java.util.List;

public interface StudentService {
    Student findOne(long id);
    Student getByName(String name);
    Student getByUserName(String studentName);
    void setStudentOnPractice(Student student, PracticeDto practiceDto);
    Student registerNewUserAccount(StudentDto accountDto, FacultyDto facultyDto) throws UserNameExistsException, EmailExistsException;
    Student registerStudent(StudentDto studentDto);
    void delete(long id);
    List<Student> getAll();
}
