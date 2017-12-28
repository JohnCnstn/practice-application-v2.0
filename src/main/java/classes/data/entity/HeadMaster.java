package classes.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "headMaster")
public class HeadMaster extends User {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "company_id", nullable = false)
    @Getter
    @Setter
    private Company company;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "headMaster")
    @JoinColumn(name = "practice_id")
    @Getter
    @Setter
    private Practice practice;
}
