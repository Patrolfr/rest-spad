package ani.fraczek.repository;

import ani.fraczek.domain.entity.metrics.SpadMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetricRepository extends JpaRepository<SpadMetric, Long> {

    Optional<SpadMetric> findByContent(String content);

}
