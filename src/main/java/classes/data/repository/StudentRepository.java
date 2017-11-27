package classes.data.repository;

import classes.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.firstName = :firstName")
    Student findByFirstName(@Param("firstName")String firstName);

    @Query("select s from Student s where s.userName = :userName")
    Student findByUserName(@Param("userName")String userName);

    @Query("select s from Student s where s.email = :email")
    Student findByEmail(@Param("email") String email);
}
