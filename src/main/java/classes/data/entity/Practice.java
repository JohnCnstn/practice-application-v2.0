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

    @Column(name = "numberOfStudents")
    @Getter
    @Setter
    private byte numberOfStudents;

    @Column(name = "enabled")
    @Getter
    @Setter
    private boolean enabled;

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
