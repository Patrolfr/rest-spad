package ani.fraczek.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Post extends AbstractEntity {

    private String title;

    private String text;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "poster_id", nullable = false)
    private User poster;



}
