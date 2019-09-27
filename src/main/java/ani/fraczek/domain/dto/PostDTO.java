package ani.fraczek.domain.dto;

import ani.fraczek.domain.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostDTO {

    private long postId;

    private String title, text;

    private String posterLogin;

    private LocalDateTime createdDate;


    public static PostDTO ofPost(Post post){
        return PostDTO.builder()
                .postId(post.getId())
                .text(post.getText())
                .title(post.getTitle())
                .posterLogin(post.getPoster().getLogin())
                .createdDate(post.getCreatedDate().toLocalDateTime())
                .build();
    }
}
