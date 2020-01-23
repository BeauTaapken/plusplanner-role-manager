package plus.planner.rolemanager.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    private String roleid;
    private String userid;
    private String projectid;
    @Enumerated(EnumType.STRING)
    private RoleType role;
}
