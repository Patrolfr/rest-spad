package ani.fraczek.domain.entity;

import ani.fraczek.domain.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends AbstractEntity {

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "poster_id", nullable = false)
    private User poster;

    public static Post ofPostDTOAndUser(final PostDTO postDTO, final User user){
        return Post.builder()
                .poster(user)
                .text(postDTO.getText())
                .title(postDTO.getTitle())
                .build();
    }


}

