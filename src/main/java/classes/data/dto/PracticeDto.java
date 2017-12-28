package classes.data.dto;

import classes.data.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(min = 1, max = 100, message = "Quantity should be from 1 to 100 students!")
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
