package classes.shedule;

import classes.model.CheckStudentStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(cron = "0 0 0 * * *", zone="Europe/Minsk")
    public void reportCurrentTime() {

        CheckStudentStatus.checkStatus();

    }
}
