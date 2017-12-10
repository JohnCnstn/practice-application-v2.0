package classes.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends User {

    @Column(name = "isBudget")
    @Getter
    @Setter
    private boolean isBudget;

    @Column(name = "avgScore")
    @Getter
    @Setter
    private double avgScore;

    @Column(name="status", nullable = false)
    @Getter
    @Setter
    private String status = StudentProfileType.AVAILABLE.getUserProfileType();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "speciality_id")
    @Getter
    @Setter
    private Speciality speciality;

//    @ManyToMany(mappedBy = "students")
//    @Getter
//    @Setter
//    private List<Practice> practices;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_practice", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "practice_id", referencedColumnName = "id"))
    @Getter
    @Setter
    private List<Practice> practices;
}
