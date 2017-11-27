package classes.data.repository;

import classes.data.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query("select u from University u where u.name = :name")
    University findByName(String name);

}
