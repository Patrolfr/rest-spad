package ani.fraczek.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "spad_user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    private String firstName, lastName;

    @NotNull
    private String login;

    @NotNull
    private String email;

    @NotNull
    private String encryptedPassword;

    @ManyToMany
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "follower_followee",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followee_id"))
    Set<User> followees;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "follower_followee",
            joinColumns = @JoinColumn(name = "followee_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    Set<User> followers;

}
