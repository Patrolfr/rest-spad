package ani.fraczek.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Foo extends AbstractEntity{

    private String name;

    private String email;

    @ManyToOne
    @JoinColumn(name = "object_type_id",
            nullable = false)
    private SpadObjType objType;

}
