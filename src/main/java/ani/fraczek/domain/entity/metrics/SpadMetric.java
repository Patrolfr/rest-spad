package ani.fraczek.domain.entity.metrics;

import ani.fraczek.domain.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpadMetric extends AbstractEntity {

    @Column(name = "uuid",
    columnDefinition = "char(36)")
    private String uuid;

    @Column(name = "chunk_sequence")
    private int chunkSequence;

    @Column(name = "session_user_login")
    private String sessionUserLogin;

    private String content;

}
