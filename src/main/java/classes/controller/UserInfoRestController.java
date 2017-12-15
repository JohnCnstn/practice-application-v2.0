package classes.controller;

import classes.data.dto.StudentDto;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userInfo")
public class UserInfoRestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/{id}/deleteStudentFromPractice", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<StudentDto> deleteStudentFromPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) {

        ArrayList practicesIds = new ArrayList();

        for (Long id : dataArrayToSend) {
            practicesIds.add(id);
        }

        studentDto.setPracticesId(practicesIds);

        deleteFromPractice(studentDto);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/setStudentOnPractice", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<StudentDto> setStudentOnPractice(@RequestBody Long[] dataArrayToSend, @ModelAttribute StudentDto studentDto) {

        ArrayList practicesIds = new ArrayList();

        for (Long id : dataArrayToSend) {
            practicesIds.add(id);
        }

        studentDto.setPracticesId(practicesIds);

        setStudentOnPractice(studentDto);

        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    private void deleteFromPractice(StudentDto studentDto) {
        studentService.deleteStudentFromPractice(studentDto);
    }

    private void setStudentOnPractice(StudentDto studentDto) {
        studentService.setStudentOnPractice(studentDto);
    }
}
