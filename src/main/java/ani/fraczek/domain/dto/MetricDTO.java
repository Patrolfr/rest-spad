package ani.fraczek.domain.dto;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MetricDTO {

    private String content;

    private int chunkSequence;
}
