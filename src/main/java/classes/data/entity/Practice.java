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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Practice practice = (Practice) o;

        if (id != practice.id) return false;
        if (quantity != practice.quantity) return false;
        if (numberOfStudents != practice.numberOfStudents) return false;
        if (enabled != practice.enabled) return false;
        if (!startDate.equals(practice.startDate)) return false;
        return endDate.equals(practice.endDate);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + (int) quantity;
        result = 31 * result + (int) numberOfStudents;
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
