package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.*;
import classes.data.entity.Faculty;
import classes.data.entity.University;
import classes.data.entity.User;
import classes.data.service.*;
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
    public ResponseEntity<PracticeDto> postPractice(@RequestBody PracticeDto practiceDto) {
        whatCreatePracticeMethodShouldBeUsed(practiceDto);
        return new ResponseEntity<>(practiceDto, HttpStatus.OK);
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

    private void createPractice(PracticeDto practiceDto) {
        practiceService.registerNewPractice(practiceDto);
    }

    private void createPractice(PracticeDto practiceDto, User user) {
        practiceService.registerPracticeWithHeadMaster(practiceDto, user);
    }

    @RequestMapping(value = "/getAllUniversities", method = RequestMethod.GET)
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> cust = universityService.getAll();
        return new ResponseEntity<>(cust, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllFaculties", method = RequestMethod.GET)
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAll();
        return new ResponseEntity<>(faculties, HttpStatus.OK);
    }

    private void whatCreatePracticeMethodShouldBeUsed (PracticeDto practiceDto) {
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

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
