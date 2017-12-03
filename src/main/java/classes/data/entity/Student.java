package classes.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

//    @Column(name = "group")
//    @Getter
//    @Setter
//    private short group;

    @Column(name="status", nullable = false)
    @Getter
    @Setter
    private String status = StudentProfileType.AVAILABLE.getUserProfileType();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "speciality_id")
    @Getter
    @Setter
    private Speciality speciality;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "practice_id")
    @Getter
    @Setter
    private Practice practice;
}
