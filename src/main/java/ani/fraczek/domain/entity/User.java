package ani.fraczek.domain.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "spad_user")
@Getter
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

}
