package classes.shedule;

import classes.data.entity.Student;
import classes.data.repository.StudentRepository;
import classes.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 0 * * *", zone="Europe/Minsk")
    public void reportCurrentTime() {

        Student student = studentService.findOne(2);
        student.setAvgScore(5);
        studentRepository.save(student);

        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
