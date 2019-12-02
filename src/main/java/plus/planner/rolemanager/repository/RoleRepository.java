package plus.planner.rolemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plus.planner.rolemanager.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r where r.userid = :userid")
    List<Role> findRolesByUserId(@Param("userid") Long userid);
}
