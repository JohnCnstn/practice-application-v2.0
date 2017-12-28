package classes.data.service.impl;

import classes.data.dto.StudentDto;
import classes.data.entity.*;
import classes.data.repository.StudentRepository;
import classes.data.repository.UserRepository;
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

import java.util.ArrayList;
import java.util.List;

@Service("studentServiceImpl")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Transactional(rollbackFor = Exception.class)
    public void setStudentsOnPractice(List<Long> practicesIds, Long[] ids) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity {
        for (Long id : ids) {

            Student student = null;

            for (Long practiceId : practicesIds) {
                Practice practice = practiceService.findOne(practiceId);

                if (studentAlreadyOnThisPractice(practice, id)) {
                    throw new StudentAlreadyOnThisPracticeException("One of students already on your practice!");
                }

                student = studentRepository.findOne(id);

                byte numberOfStudents = practice.getNumberOfStudents();
                numberOfStudents++;
                byte quantity = practice.getQuantity();
                if (numberOfStudents == quantity) {
                    practice.setEnabled(false);
                }
                if (numberOfStudents > quantity) {
                    throw new NumberOfStudentsEqualsQuantity("There are fewer places left than students!");
                }
                practice.setNumberOfStudents(numberOfStudents);

                List<Practice> studentPractice = getStudentPractices(id);

                studentPractice.add(practice);

                student.setPractices(studentPractice);

                student.setStatus(CheckStudentStatus.checkStatus(practice));

            }

            if (student != null) {
                studentRepository.save(student);
            }
        }
    }

    @Override
    @Transactional
    public void deleteStudentFromPractice(List<Long> practicesIds, Long[] ids) {
        for (Long id : ids) {

            Student student = null;

            for (Long practiceId : practicesIds) {

                Practice practice = practiceService.findOne(practiceId);

                student = studentRepository.findOne(id);

                byte numberOfStudents = practice.getNumberOfStudents();
                numberOfStudents--;
                if (numberOfStudents == 0) {
                    practice.setEnabled(true);
                }

                practice.setNumberOfStudents(numberOfStudents);

                List<Practice> studentPractice = getStudentPractices(id);

                studentPractice.remove(practice);

                student.setPractices(studentPractice);

                practice.setEnabled(true);

                student.setStatus("AVAILABLE");

            }

            if (student != null) {
                studentRepository.save(student);
            }
        }
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
        User user = userRepository.findByUserName(userName);
        return user != null;
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private boolean studentAlreadyOnThisPractice(Practice headMasterPractice, long id) {

        List<Practice> allStudentsPractices = new ArrayList();

        List<Practice> studentPractices = getStudentPractices(id);
        for (Practice practice : studentPractices) {
            allStudentsPractices.add(practice);
        }

        for (Practice practice : allStudentsPractices) {
            if (practice.equals(headMasterPractice)) {
                return true;
            }
        }
        return false;
    }
}
