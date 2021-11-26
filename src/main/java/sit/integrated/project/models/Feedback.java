package sit.integrated.project.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "Feedback")
@Getter@Setter

public class Feedback {

    @Id

    @Column( name ="feedbackId")
    private Integer feedbackId;

    @Column( name ="message")
    private String message;

    @Column(name="users_userId")
    private Integer usersId;

    @Column(name="products_productId")
    private Integer productsId;


}
