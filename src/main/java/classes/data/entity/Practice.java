package classes.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "practice")
public class Practice {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
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

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
    @Getter
    @Setter
    private Student student;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "headMaster_id")
    @Getter
    @Setter
    private HeadMaster headMaster;
}
