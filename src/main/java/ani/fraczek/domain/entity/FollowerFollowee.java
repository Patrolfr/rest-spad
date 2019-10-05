package ani.fraczek.domain.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "follower_followee")
@NoArgsConstructor
public class FollowerFollowee {

    @EmbeddedId
    private FollowCopositId followCopositId;


    public FollowerFollowee(long followerId, long followeeId){
        this.followCopositId = new FollowCopositId(followerId, followeeId);
    }

    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    static class FollowCopositId implements Serializable {

        @Column(name = "follower_id")
        private long followerId;

        @Column(name = "followee_id")
        private long followeeId;
    }
}
