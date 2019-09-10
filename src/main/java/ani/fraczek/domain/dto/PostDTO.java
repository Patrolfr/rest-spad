package ani.fraczek.domain.dto;

import ani.fraczek.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
