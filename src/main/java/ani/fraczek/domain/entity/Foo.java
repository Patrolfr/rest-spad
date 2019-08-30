package ani.fraczek.domain.entity;

import ani.fraczek.domain.definition.SpadObjType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Foo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private String email;

    @OneToOne
    @JoinColumn(name = "object_type_id",
            nullable = false)
    private SpadObjType objType;

}
