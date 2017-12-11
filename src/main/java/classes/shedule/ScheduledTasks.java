package classes.shedule;

import classes.data.entity.Practice;
import classes.data.entity.Student;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private StudentService studentService;

    @Scheduled(cron = "0 0 0 * * *", zone="Europe/Minsk")
    public void reportCurrentTime() {

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
}
