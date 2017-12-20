package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.*;
import classes.data.entity.*;
import classes.data.service.*;
import classes.data.validation.exception.HeadMasterAlreadyHavePractice;
import classes.data.validation.exception.practice.NumberOfStudentsEqualsQuantity;
import classes.data.validation.exception.studentOnPractice.StudentAlreadyOnThisPracticeException;
import classes.data.validation.exception.studentOnPractice.StudentNotOnYourPracticeException;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsRestController {

    private final UniversityService universityService;

    private final FacultyService facultyService;

    @Autowired
    private HeadMasterService headMasterService;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    public StudentsRestController(UniversityService universityService, FacultyService facultyService) {
        this.universityService = universityService;
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/postUniversity", method = RequestMethod.POST)
    public ResponseEntity<UniversityDto> postUniversity(@RequestBody UniversityDto universityDto) {
        createUniversity(universityDto);
        return new ResponseEntity<>(universityDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/postFaculty", method = RequestMethod.POST)
    public ResponseEntity<FacultyDto> postFaculty(@RequestBody FacultyDto facultyDto) {
        createFaculty(facultyDto);
        return new ResponseEntity<>(facultyDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/postSpeciality", method = RequestMethod.POST)
    public ResponseEntity<SpecialityDto> postSpeciality(@RequestBody SpecialityDto specialityDto) {
        createSpeciality(specialityDto);
        return new ResponseEntity<>(specialityDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/postHeadMaster", method = RequestMethod.POST)
    public ResponseEntity<HeadMasterDto> postHeadMaster(@RequestBody HeadMasterDto headMasterDto) {
        createHeadMasterAccount(headMasterDto);
        return new ResponseEntity<>(headMasterDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/postStudent", method = RequestMethod.POST)
    public ResponseEntity<StudentDto> postStudent(@RequestBody StudentDto studentDto) {
        createStudent(studentDto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/postPractice", method = RequestMethod.POST)
    public ResponseEntity<PracticeDto> postPractice(@RequestBody PracticeDto practiceDto) throws HeadMasterAlreadyHavePractice {
        whatCreatePracticeMethodShouldBeUsed(practiceDto);
        return new ResponseEntity<>(practiceDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/assignOnPractice", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> assignOnPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity {

        User headMaster = getPrincipal();

        Practice practice = getHeadMasterPractice(headMaster.getId());

        List practices = new ArrayList();

        practices.add(practice.getId());

        studentDto.setPracticesId(practices);

        for (Long id : dataArrayToSend) {
            studentDto.setId(id);
            setStudentOnPractice(studentDto);
        }

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/headMasterRemoveFromPractice", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> headMasterRemoveFromPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) throws StudentAlreadyOnThisPracticeException, StudentNotOnYourPracticeException {

        HeadMaster headMaster = (HeadMaster) getPrincipal();

        for (Long id : dataArrayToSend) {
            studentDto.setId(id);
            if (studentNotOnYourPractice(headMaster, studentDto)) {
                throw new StudentNotOnYourPracticeException();
            }
        }

        Practice practice = getHeadMasterPractice(headMaster.getId());

        List practices = new ArrayList();

        practices.add(practice.getId());

        studentDto.setPracticesId(practices);

        for (Long id : dataArrayToSend) {
            studentDto.setId(id);
            deleteFromPractice(studentDto);
        }

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteStudents", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> deleteStudents(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) {

        for (Long id : dataArrayToSend) {
            deleteStudent(id);
        }

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    private void createFaculty(FacultyDto facultyDto) {
        facultyService.registerNewFaculty(facultyDto);
    }

    private void createSpeciality(SpecialityDto specialityDto) {
        specialityService.registerNewSpeciality(specialityDto);
    }

    private void createUniversity(UniversityDto universityDto) {
        universityService.registerNewUniversity(universityDto);
    }

    private void createHeadMasterAccount(HeadMasterDto headMasterDto) {
        headMasterService.registerNewHeadMasterAccount(headMasterDto);
    }

    private void createStudent(StudentDto studentDto) {
        studentService.registerStudent(studentDto);
    }

    private void createPractice(PracticeDto practiceDto) throws HeadMasterAlreadyHavePractice {
        practiceService.registerNewPractice(practiceDto);
    }

    private void createPractice(PracticeDto practiceDto, User user) throws HeadMasterAlreadyHavePractice {
        practiceService.registerPracticeWithHeadMaster(practiceDto, user);
    }

    @RequestMapping(value = "/getAllUniversities", method = RequestMethod.GET)
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> universityList = universityService.getAll();
        return new ResponseEntity<>(universityList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllFaculties", method = RequestMethod.GET)
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAll();
        return new ResponseEntity<>(faculties, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllSpecialities", method = RequestMethod.GET)
    public ResponseEntity<List<Faculty>> getAllSpecialities() {
        List<Faculty> faculties = facultyService.getAll();
        return new ResponseEntity<>(faculties, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllHeadMasters", method = RequestMethod.GET)
    public ResponseEntity<List<HeadMasterDto>> getAllHeadMasters() {
        List<HeadMaster> headMasters = headMasterService.getAll();
        List<HeadMasterDto> headMasterDtoList = new ArrayList<>();

        setHeadMasterDto(headMasters, headMasterDtoList);

        return new ResponseEntity<>(headMasterDtoList, HttpStatus.OK);
    }

    private void setHeadMasterDto(List<HeadMaster> headMasters, List<HeadMasterDto> headMasterDtoList) {
        for (HeadMaster headMaster : headMasters) {
            HeadMasterDto headMasterDto = new HeadMasterDto();
            headMasterDto.setUserName(headMaster.getUserName());
            headMasterDto.setId(headMaster.getId());
            headMasterDtoList.add(headMasterDto);
        }
    }

    private void whatCreatePracticeMethodShouldBeUsed (PracticeDto practiceDto) throws HeadMasterAlreadyHavePractice {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                createPractice(practiceDto);
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_HEAD_MASTER")) {
                createPractice(practiceDto, getPrincipal());
                break;
            }
        }
    }

    private void setStudentOnPractice(StudentDto studentDto) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity {
        studentService.setStudentOnPractice(studentDto);
    }

    private void deleteStudent(long id) {
        studentService.delete(id);
    }

    private Practice getHeadMasterPractice (long id) {

        HeadMaster headMaster = headMasterService.findOne(id);

        return headMaster.getPractice();
    }

    private void deleteFromPractice(StudentDto studentDto) {
        studentService.deleteStudentFromPractice(studentDto);
    }

    private boolean studentNotOnYourPractice(HeadMaster headMaster, StudentDto studentDto) {

        Practice headMasterPractice = headMasterService.getPractice(headMaster.getId());

        List<Practice> practiceList = studentService.getStudentPractices(studentDto.getId());

        for (Practice practice: practiceList) {
            if (practice.equals(headMasterPractice)) {
                return false;
            }
        }
        return true;
    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
