package classes.data.dto;

import lombok.Getter;
import lombok.Setter;

public class FacultyDto {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private long universityId;

    @Getter
    @Setter
    private String name;
}
