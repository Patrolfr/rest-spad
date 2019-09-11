package ani.fraczek.domain.dto;

import ani.fraczek.domain.entity.Post;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostDTO {

    private String title, text;

    private Long posterId;

    public static PostDTO ofPost(Post post){
        return PostDTO.builder()
                .text(post.getText())
                .title(post.getTitle())
                .posterId(post.getPoster().getId())
                .build();
    }
}
