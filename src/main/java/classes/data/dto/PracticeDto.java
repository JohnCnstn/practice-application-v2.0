package classes.data.dto;

import classes.data.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class PracticeDto {
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private Date startDate;

    @Getter
    @Setter
    private Date endDate;

    @Getter
    @Setter
    private long studentId;

    @Getter
    @Setter
    private byte quantity;

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private long headMasterId;

    @Getter
    @Setter
    private String headMasterName;

    @Getter
    @Setter
    private String companyName;
}
