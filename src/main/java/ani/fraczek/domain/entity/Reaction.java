package ani.fraczek.domain.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Builder
@Getter
@Table(name = "reaction")
public class Reaction extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "object_type_id",
            nullable = false)
    private SpadObjType reactionType;

    private String title;

    private String content;

}
