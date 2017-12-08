package classes.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ListOfStudentsIdsDto {
    @Getter
    @Setter
    boolean checkbox = true;
    @Getter
    @Setter
    List<String> students;
}
