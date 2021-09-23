package sit.integrated.project.models;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "Brands")
@Getter@Setter
public class Brands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandid")
    private String brandId;

    @Column(name = "brandname")
    private String brandName;
}
