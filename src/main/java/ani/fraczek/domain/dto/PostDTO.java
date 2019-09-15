package ani.fraczek.domain.dto;

import ani.fraczek.domain.entity.Post;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostDTO {

    private String title, text;

    private String posterLogin;

    public static PostDTO ofPost(Post post){
        return PostDTO.builder()
                .text(post.getText())
                .title(post.getTitle())
                .posterLogin(post.getPoster().getLogin())
                .build();
    }
}
