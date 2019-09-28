package ani.fraczek.domain.dto;

import ani.fraczek.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {

    private long id;

    private String firstName, lastName;

    private String login;

    private String email;

    private OffsetDateTime createdDate;

    public static UserDTO ofUser(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .build();
    }

}
