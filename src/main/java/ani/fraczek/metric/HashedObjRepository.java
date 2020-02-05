package ani.fraczek.metric;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashedObjRepository extends CrudRepository<HashedObj, String> {}

