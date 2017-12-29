package classes.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HeadMasterDto {

    @Getter
    @Setter
    private long id;

    @NotNull
    @Size(min = 2, max = 10, message = "Your first name should be from 2 to 10 symbols!")
    @Getter
    @Setter
    private String firstName;

    @NotNull
    @Size(min = 3, max = 10, message = "Your last name should be from 2 to 10 symbols!")
    @Getter
    @Setter
    private String lastName;

    @NotNull
    @Size(min = 5, max = 10, message = "Your username should be from 5 to 10 symbols!")
    @Getter
    @Setter
    private String userName;

    @NotNull
    @Size(min = 5, max = 20, message = "Your password should be from 5 to 20 symbols!")
    @Getter
    @Setter
    private String password;

    @NotNull
    @Size(min = 5, max = 20, message = "Your email should be from 5 to 20 symbols!")
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private long companyId;

}
