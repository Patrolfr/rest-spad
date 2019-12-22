package ani.fraczek.service.processing;

import ani.fraczek.controller.MetricController;
import ani.fraczek.domain.dto.MetricDTO;
import ani.fraczek.domain.entity.metrics.SpadMetric;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledService {

    private static final Logger log = LoggerFactory.getLogger(ScheduledService.class);

    private final MetricClient metricClient;

    private final MetricController metricController;

    @Scheduled(cron = "*/10 * * ? * *")
    public void logLog(){
        log.info("logLog: {}", LocalDateTime.now().toString());
    }

//    @Scheduled(cron = "*/2 * * ? * *")
    public void addMetric(){
        String now = LocalDateTime.now().toString();
        log.info("addMetric: {}", now);
        ResponseEntity responseEntity = metricController.newMetric(MetricDTO.builder().content("created " + now).build());
        log.info("addMetric response: {}", responseEntity.getStatusCode().toString());
    }

//    @Scheduled(cron = "*/1 * * ? * *")
    public void postForMetric(){
        String now = LocalDateTime.now().toString();
        log.info("postForMetric: {}", now);

        SpadMetric spadMetric = metricClient.postForNewMetric(MetricDTO.builder().content("created " + now).build());

        log.info("addMetric response: {}", spadMetric);
    }


}
