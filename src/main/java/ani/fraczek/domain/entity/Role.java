package ani.fraczek.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Role {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

}
