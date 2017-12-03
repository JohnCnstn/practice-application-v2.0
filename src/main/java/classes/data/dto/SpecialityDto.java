package classes.data.dto;

import lombok.Getter;
import lombok.Setter;

public class SpecialityDto {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long facultyId;

    @Getter
    @Setter
    private String name;
}
