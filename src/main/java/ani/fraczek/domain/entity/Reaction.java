package ani.fraczek.domain.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "reaction")
public class Reaction extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

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
