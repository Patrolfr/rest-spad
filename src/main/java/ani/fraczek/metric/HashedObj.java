package ani.fraczek.metric;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Getter
@RedisHash("HashedObj")
@AllArgsConstructor
public class HashedObj implements Serializable {

    public enum Type {
        STRICT, NON_STRICT
    }

    private String id;
    private String name;
    private Type type;
    private int grade;

}
