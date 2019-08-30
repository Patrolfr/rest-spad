package ani.fraczek.domain.definition;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "spad_obj_type")
@Getter
public class SpadObjType {

    @Id
    private Long id;

    private String type;

    @Column(name = "last_modified")
    OffsetDateTime lastModified;

}
