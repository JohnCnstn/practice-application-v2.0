package classes.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SpecialityDto {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long facultyId;

    @NotNull
    @Size(min = 3, max = 10)
    @Getter
    @Setter
    private String name;
}
