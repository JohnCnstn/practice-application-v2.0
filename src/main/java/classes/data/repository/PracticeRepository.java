package classes.data.repository;

import classes.data.entity.Practice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticeRepository extends JpaRepository<Practice, Long> {
    List<Practice> findAllByEnabled(Boolean enabled);
}
