package classes.model;

import classes.data.entity.Practice;
import classes.data.entity.Student;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CheckStudentStatus {

    @Autowired
    private static StudentService studentService;

    public static void checkStatus() {
        List<Student> students = studentService.getAll();

        for (Student student : students) {
            List<Practice> practices = studentService.getStudentPractices(student.getId());
            checkStatus(practices);
        }
    }

    public static String checkStatus(List<Practice> practices) {

        String status = "AVAILABLE";

        for (Practice practice : practices) {

            Date today = new Date();

            if (today.after(practice.getStartDate()) && today.before(practice.getEndDate())) {
                return "ON_PRACTICE";
            } else if (today.before(practice.getStartDate())) {
                status = "WAITING";
            } else {
                status = "AVAILABLE";
            }
        }
        return status;
    }
}
