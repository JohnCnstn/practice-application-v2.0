package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.*;
import classes.data.entity.User;
import classes.data.service.FacultyService;
import classes.data.service.HeadMasterService;
import classes.data.service.UniversityService;
import classes.data.validation.exception.EmailExistsException;
import classes.data.validation.exception.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UniversityService universityService;

    private final FacultyService facultyService;

    @Autowired
    private HeadMasterService headMasterService;

    @Autowired
    public AdminRestController(UniversityService universityService, FacultyService facultyService) {
        this.universityService = universityService;
        this.facultyService = facultyService;
    }

    @RequestMapping(value = "/postPractice", method = RequestMethod.POST)
    public ResponseEntity<UniversityDto> postPractice(@RequestBody UniversityDto universityDto) {
        createUniversity(universityDto);
        return new ResponseEntity<>(universityDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/postFaculty", method = RequestMethod.POST)
    public ResponseEntity<FacultyDto> postFaculty(@RequestBody FacultyDto facultyDto, UniversityDto universityDto) {
        createFaculty(facultyDto);
        return new ResponseEntity<>(facultyDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/postHeadMaster", method = RequestMethod.POST)
    public ResponseEntity<HeadMasterDto> postHeadMaster(@RequestBody HeadMasterDto headMasterDto) {
        createHeadMasterAccount(headMasterDto);
        return new ResponseEntity<>(headMasterDto, HttpStatus.OK);
    }

    private void createFaculty(FacultyDto facultyDto) {
        facultyService.registerNewFaculty(facultyDto);
    }

    private void createUniversity(UniversityDto universityDto) {
        universityService.registerNewUniversity(universityDto);
    }

    private void createHeadMasterAccount(HeadMasterDto headMasterDto) {
        headMasterService.registerNewHeadMasterAccount(headMasterDto);
    }

    private User getPrincipal(){
        CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetail.getUser();
    }
}
