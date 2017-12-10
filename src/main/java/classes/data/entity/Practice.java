package classes.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "practice")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private long id;

    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date endDate;

    @Column(name = "quantity")
    @Getter
    @Setter
    private byte quantity;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "practice_student", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "practice_id", referencedColumnName = "id"))
//    @Getter
//    @Setter
//    private List<Student> students;

    @ManyToMany(mappedBy = "practices")
    @Getter
    @Setter
    private List<Student> students;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "headMaster_id")
    @Getter
    @Setter
    private HeadMaster headMaster;
}
