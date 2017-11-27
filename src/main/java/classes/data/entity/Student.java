package classes.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student extends User {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "faculty_id")
    @Getter
    @Setter
    private Faculty faculty;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "practice_id")
    @Getter
    @Setter
    private Practice practice;
}
