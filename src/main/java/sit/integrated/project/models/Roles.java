package sit.integrated.project.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "Roles")
@Getter@Setter
public class Roles {
    public Roles(Integer roleId,String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
    public Roles() {
    }


    @Id
    @Column(name ="roleId")
    private Integer roleId;

    @Column(name ="roleName")
    private String roleName;

}