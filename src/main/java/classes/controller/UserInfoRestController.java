package classes.controller;

import classes.data.detail.CustomUserDetail;
import classes.data.dto.StudentDto;
import classes.data.entity.HeadMaster;
import classes.data.entity.Practice;
import classes.data.entity.User;
import classes.data.service.HeadMasterService;
import classes.data.service.StudentService;
import classes.data.validation.exception.NewPracticeDateInsideOldPractice;
import classes.data.validation.exception.practice.NumberOfStudentsEqualsQuantity;
import classes.data.validation.exception.studentOnPractice.StudentAlreadyOnThisPracticeException;
import classes.data.validation.exception.studentOnPractice.StudentNotOnYourPracticeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoRestController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private HeadMasterService headMasterService;

    @RequestMapping(value = "/{id}/removeFromPractice", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> removeFromPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) {

        List<Long> practicesIds = new ArrayList();

        for (Long id : dataArrayToSend) {
            practicesIds.add(id);
        }

        deleteFromPractice(practicesIds , studentDto.getId());

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/assignOnPractice", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> assignOnPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity, NewPracticeDateInsideOldPractice {

        List<Long> practicesIds = new ArrayList();

        for (Long id : dataArrayToSend) {
            practicesIds.add(id);
        }

        setStudentOnPractice(practicesIds ,studentDto.getId());

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/headMasterAssignOnPractice", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<StudentDto> headMasterAssignOnPractice(@ModelAttribute StudentDto studentDto) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity, NewPracticeDateInsideOldPractice {

        User headMaster = getPrincipal();

        Practice practice = getHeadMasterPractice(headMaster.getId());

        List<Long> practicesIds = new ArrayList<>();
        practicesIds.add(practice.getId());

        setStudentOnPractice(practicesIds, studentDto.getId());

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/headMasterRemoveFromPractice", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<StudentDto> headMasterRemoveFromPractice(@ModelAttribute StudentDto studentDto) throws StudentNotOnYourPracticeException {

        HeadMaster headMaster = (HeadMaster) getPrincipal();

        if (studentNotOnYourPractice(headMaster, studentDto)) {
            throw new StudentNotOnYourPracticeException("One of students not on your practice exception!");
        }

        Practice practice = getHeadMasterPractice(headMaster.getId());

        List<Long> practicesIds = new ArrayList<>();
        practicesIds.add(practice.getId());

        deleteFromPractice(practicesIds, studentDto.getId());

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    private void deleteFromPractice(List<Long> practicesIds, long id) {
        Long[] ids = {id};
        studentService.deleteStudentFromPractice(practicesIds, ids);
    }

    private void setStudentOnPractice(List<Long> practicesIds, long id) throws StudentAlreadyOnThisPracticeException, NumberOfStudentsEqualsQuantity, NewPracticeDateInsideOldPractice {
        Long[] ids = {id};
        studentService.setStudentsOnPractice(practicesIds, ids);
    }

    private Practice getHeadMasterPractice (long id) {

        HeadMaster headMaster = headMasterService.findOne(id);

        return headMaster.getPractice();
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
