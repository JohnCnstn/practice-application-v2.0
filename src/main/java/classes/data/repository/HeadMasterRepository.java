package classes.data.repository;

import classes.data.entity.HeadMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeadMasterRepository extends JpaRepository<HeadMaster, Long> {
    @Query("select h from HeadMaster h where h.userName = :userName")
    HeadMaster findByUserName(@Param("userName")String userName);

    @Query("select h from HeadMaster h where h.email = :email")
    HeadMaster findByEmail(@Param("email") String email);
}
