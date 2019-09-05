package ani.fraczek.domain.entity;

import ani.fraczek.domain.definition.SpadObjType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Foo extends AbstractEntity{

    private String name;

    private String email;

    @OneToOne
    @JoinColumn(name = "object_type_id",
            nullable = false)
    private SpadObjType objType;

}
