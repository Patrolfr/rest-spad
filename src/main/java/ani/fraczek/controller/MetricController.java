package ani.fraczek.controller;

import ani.fraczek.domain.dto.MetricDTO;
import ani.fraczek.domain.entity.metrics.SpadMetric;
import ani.fraczek.repository.MetricRepository;
import ani.fraczek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricController {

    private final MetricRepository metricRepository;

    private final UserService userService;

    @PostMapping
    public ResponseEntity newMetric(@RequestBody MetricDTO metricDTO) {
        SpadMetric save = metricRepository.save(SpadMetric.builder()
                .content(metricDTO.getContent())
                .sessionUserLogin(userService.getCurrentUserLogin())
                .chunkSequence(metricDTO.getChunkSequence())
                .uuid(UUID.randomUUID().toString())
                .build());
        return ResponseEntity.ok(save);
    }



}
