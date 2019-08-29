package ani.fraczek.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spad_obj_type")
@Getter
public class SpadObjType {

    @Id
    private Long id;

    private String name;


}
