package ani.fraczek.repository;

import ani.fraczek.domain.entity.metrics.SpadMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<SpadMetric, Long> {
}
