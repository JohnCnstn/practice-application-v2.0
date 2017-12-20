package classes.data.service.impl;

import classes.data.dto.StudentDto;
import classes.data.entity.*;
import classes.data.repository.StudentRepository;
import classes.data.service.PracticeService;
import classes.data.service.SpecialityService;
import classes.data.service.StudentService;
import classes.data.validation.exception.practice.NumberOfStudentsEqualsQuantity;
import classes.data.validation.exception.signUp.EmailExistsException;
import classes.data.validation.exception.signUp.UserNameExistsException;
import classes.data.validation.exception.studentOnPractice.StudentAlreadyOnThisPracticeException;
import classes.model.CheckStudentStatus;
import org.hibernate.Hibernate;
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
    private PracticeService practiceService;

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

    public Student setStudentOnPractice(StudentDto studentDto) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity {

        Student student = studentRepository.findOne(studentDto.getId());

        List<Practice> practices = student.getPractices();

        if (studentAlreadyOnThisPractice(practices)) {
            throw new StudentAlreadyOnThisPracticeException();
        }

        for (Long practiceId : studentDto.getPracticesId()) {

            practices.add(practiceService.findOne(practiceId));

        }

        for (Practice practice : practices) {
            byte numberOfStudents = practice.getNumberOfStudents();
            numberOfStudents++;
            byte quantity = practice.getQuantity();
            if (numberOfStudents == quantity) {
                practice.setEnabled(false);
            }
            if (numberOfStudents > quantity) {
                throw new NumberOfStudentsEqualsQuantity();
            }
            practice.setNumberOfStudents(numberOfStudents);
        }

        student.setPractices(practices);

        studentDto = CheckStudentStatus.checkStatus(studentDto, practices);

        student.setStatus(studentDto.getStatus());

        return studentRepository.save(student);
    }

    public Student setStudentsOnPractice(StudentDto studentDto, List id) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity {

        Student student = studentRepository.findOne(studentDto.getId());

        List<Practice> practices = student.getPractices();

        if (studentAlreadyOnThisPractice(practices)) {
            throw new StudentAlreadyOnThisPracticeException();
        }

        for (Long practiceId : studentDto.getPracticesId()) {

            practices.add(practiceService.findOne(practiceId));

        }

        for (Practice practice : practices) {
            byte numberOfStudents = practice.getNumberOfStudents();
            numberOfStudents++;
            byte quantity = practice.getQuantity();
            if (numberOfStudents == quantity) {
                practice.setEnabled(false);
            }
            if (numberOfStudents > quantity) {
                throw new NumberOfStudentsEqualsQuantity();
            }
            practice.setNumberOfStudents(numberOfStudents);
        }

        student.setPractices(practices);

        studentDto = CheckStudentStatus.checkStatus(studentDto, practices);

        student.setStatus(studentDto.getStatus());

        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student deleteStudentFromPractice(StudentDto studentDto) {

        Student student = studentRepository.findOne(studentDto.getId());

        List practices = student.getPractices();

        for (Long practiceId : studentDto.getPracticesId()) {

            practices.remove(practiceService.findOne(practiceId));

        }

        student.setPractices(practices);

        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public List getStudentPractices(long id) {
        Student student = studentRepository.findOne(id);
        Hibernate.initialize(student.getPractices());
        List<Practice> orders = student.getPractices();
        return orders;
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

    @Override
    public Student changeStatus(Student student) {
        student.setStatus("ON_PRACTICE");
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

    private boolean studentAlreadyOnThisPractice(List<Practice> practices) {

        List<Practice> practiceList = practiceService.getAll();

        for (Practice practice: practiceList) {
            for (Practice setPractice : practices) {
                if (practice.equals(setPractice)) {
                    return true;
                }
            }
        }
        return false;
    }
}
