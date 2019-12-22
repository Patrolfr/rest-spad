package ani.fraczek.service.processing;

import ani.fraczek.domain.dto.MetricDTO;
import ani.fraczek.domain.entity.metrics.SpadMetric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(name = "MetricClient", url = "localhost:8080")
public interface MetricClient {

    @PostMapping(value = "/metrics")
    SpadMetric postForNewMetric(@RequestBody MetricDTO metricDTO);

}
