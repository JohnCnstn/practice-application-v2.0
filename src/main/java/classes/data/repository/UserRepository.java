package classes.data.repository;

import classes.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(@Param("userName")String userName);
    User findByEmail(@Param("email")String email);
}
