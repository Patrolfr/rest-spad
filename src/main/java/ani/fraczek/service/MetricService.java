package ani.fraczek.service;

import ani.fraczek.domain.dto.MetricDTO;
import ani.fraczek.domain.entity.metrics.SpadMetric;
import ani.fraczek.repository.MetricRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.LockAcquisitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetricService {

    private static final Logger log = LoggerFactory.getLogger(MetricService.class);

    private final MetricRepository metricRepository;

    private final UserService userService;


    @Retryable(value = {TransactionException.class, JpaSystemException.class, LockAcquisitionException.class},
            maxAttempts = 4,
            backoff = @Backoff(value = 500L))
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public SpadMetric addMetric(MetricDTO metricDTO) {
        log.debug("metricDTO: {}", metricDTO);

        Optional<SpadMetric> byContent = metricRepository.findByContent(metricDTO.getContent());

        if (byContent.isEmpty()) {
            return metricRepository.save(SpadMetric.builder()
                    .content(metricDTO.getContent())
                    .sessionUserLogin(userService.getCurrentUserLogin())
                    .chunkSequence(metricDTO.getChunkSequence())
                    .uuid(UUID.randomUUID().toString())
                    .build());
        }
        throw new MetricException("Metric exists: " + byContent.get().getContent());
    }
}
