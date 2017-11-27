package classes.objects.search;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SearchCriteria {

    @Getter
    @Setter
    private String firstName;

}
