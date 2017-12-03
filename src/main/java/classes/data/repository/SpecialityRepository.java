package classes.data.repository;

import classes.data.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
}
