package ani.fraczek.repository;

import ani.fraczek.domain.entity.SpadObjType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpadObjTypeRepository extends JpaRepository<SpadObjType, Long> {

    Optional<SpadObjType> findByType(String type);

    SpadObjType findOneByType(String type);
}
