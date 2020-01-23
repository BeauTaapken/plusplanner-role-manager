package plus.planner.rolemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plus.planner.rolemanager.model.User;

import javax.persistence.Tuple;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u as user, r as role FROM User u LEFT JOIN Role r ON u.userid = r.userid WHERE r.projectid = :projectid")
    List<Tuple> findUsersByRole(@Param("projectid") String projectid);
}
