package ani.fraczek.domain.dto;

import ani.fraczek.domain.entity.Reaction;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class ReactionDTO {

    private String authorLogin;

    private Long postId;

    private String title;

    private String content;

    private String reactionType;

    private OffsetDateTime createdDate;

    public static ReactionDTO ofReaction(Reaction reaction){
        return ReactionDTO.builder()
                .authorLogin(reaction.getAuthor().getLogin())
                .postId(reaction.getPost().getId())
                .title(reaction.getTitle())
                .content(reaction.getContent())
                .reactionType(reaction.getReactionType().getType())
                .build();
    }

}
