package classes.data.service.impl;

import classes.data.dto.FacultyDto;
import classes.data.dto.PracticeDto;
import classes.data.dto.UserDto;
import classes.data.entity.*;
import classes.data.repository.StudentRepository;
import classes.data.service.FacultyService;
import classes.data.service.StudentService;
import classes.data.validation.exception.EmailExistsException;
import classes.data.validation.exception.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyService facultyServiceImpl;

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Student findOne(long id) {
        return studentRepository.findOne(id);
    }

    public Student getByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public Student getByUserName(String studentName) {
        return studentRepository.findByUserName(studentName);
    }

    public void setStudentOnPractice(Student student, PracticeDto practiceDto) {

        Practice practice = new Practice();
        practice.setStartDate(practiceDto.getStartDate());

        student.setPractice(practice);

        studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public Student registerNewUserAccount(UserDto accountDto, FacultyDto facultyDto) throws UserNameExistsException, EmailExistsException {

        if (userNameExists(accountDto.getUserName())) {
            throw new UserNameExistsException("There is an account with that Username: "  + accountDto.getUserName());
        }

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: "  + accountDto.getEmail());
        }

        Student student = new Student();

        Faculty faculty = facultyServiceImpl.findOne(facultyDto.getId());

        student.setFirstName(accountDto.getFirstName());
        student.setLastName(accountDto.getLastName());
        student.setEmail(accountDto.getEmail());
        student.setUserName(accountDto.getUserName());

        student.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));

        student.setFaculty(faculty);

        student.setUserProfile(userProfileService.getByType("STUDENT"));

        return studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.delete(id);
    }

    private boolean userNameExists(String userName) {
        User user = studentRepository.findByUserName(userName);
        return user != null;
    }

    private boolean emailExist(String email) {
        User user = studentRepository.findByEmail(email);
        return user != null;
    }
}
