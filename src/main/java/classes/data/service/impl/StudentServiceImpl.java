package classes.data.service.impl;

import classes.data.dto.PracticeDto;
import classes.data.dto.StudentDto;
import classes.data.entity.*;
import classes.data.repository.StudentRepository;
import classes.data.service.FacultyService;
import classes.data.service.SpecialityService;
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
    private UserProfileServiceImpl userProfileService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Student findOne(long id) {
        return studentRepository.findOne(id);
    }

    public Student getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
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
    public Student registerNewUserAccount(StudentDto studentDto) throws UserNameExistsException, EmailExistsException {

        if (userNameExists(studentDto.getUserName())) {
            throw new UserNameExistsException("There is an account with that Username: "  + studentDto.getUserName());
        }

        if (emailExist(studentDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: "  + studentDto.getEmail());
        }

        Student student = new Student();

        Speciality speciality = specialityService.findOne(studentDto.getSpecialityId());

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setUserName(studentDto.getUserName());
        student.setAvgScore(studentDto.getAvgScore());
        student.setBudget(studentDto.isBudget());

        student.setPassword(bCryptPasswordEncoder.encode(studentDto.getPassword()));

        student.setSpeciality(speciality);

        student.setUserProfile(userProfileService.getByType("STUDENT"));

        return studentRepository.save(student);
    }

    @Transactional
    public Student registerStudent(StudentDto studentDto) {

        Student student = new Student();

        Speciality speciality = specialityService.findOne(studentDto.getSpecialityId());

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setUserName(studentDto.getUserName());
        student.setAvgScore(studentDto.getAvgScore());
        student.setBudget(studentDto.isBudget());

        student.setPassword(bCryptPasswordEncoder.encode(studentDto.getPassword()));

        student.setSpeciality(speciality);

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
