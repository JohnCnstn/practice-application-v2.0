package classes.model;

import classes.data.dto.StudentDto;
import classes.data.entity.Practice;
import classes.data.entity.Student;
import classes.data.service.PracticeService;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CheckStudentStatus {

    @Autowired
    private static StudentService studentService;

    public static void checkStatus() {
        Date today = new Date();

        List<Student> students = studentService.getAll();

        for (Student student : students) {
            List<Practice> practices = studentService.getStudentPractices(student.getId());
            for (Practice practice : practices) {
                if(today.after(practice.getStartDate()) && today.before(practice.getEndDate())) {
                    studentService.changeStatus(student);
                }
            }
        }
    }

    public static String checkStatus(Practice practice) {
        Date today = new Date();

        String status;

        if(today.after(practice.getStartDate()) && today.before(practice.getEndDate())) {
            status = "ON_PRACTICE";
        } else if (today.before(practice.getStartDate())) {
            status = "WAITING";
            return status;
        } else {
            status = "AVAILABLE";
        }
        return status;
    }
}
