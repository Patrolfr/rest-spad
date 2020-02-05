package ani.fraczek.controller;

import ani.fraczek.domain.dto.MetricDTO;
import ani.fraczek.domain.entity.metrics.SpadMetric;
import ani.fraczek.metric.HashedObj;
import ani.fraczek.metric.HashedObjRepository;
import ani.fraczek.repository.MetricRepository;
import ani.fraczek.service.MetricService;
import ani.fraczek.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricController {

    private final MetricRepository metricRepository;

    private final UserService userService;

    private final MetricService metricService;

    private final HashedObjRepository hashedObjRepository;

    @PostMapping
    public ResponseEntity newMetric(@RequestBody MetricDTO metricDTO) {
        SpadMetric save = metricService.addMetric(metricDTO);
        return ResponseEntity.ok(save);
    }

    @PostMapping(value = "/hashedObj")
    public ResponseEntity newHashedObj(@RequestBody MetricDTO metricDTO) {

        HashedObj save = hashedObjRepository.save(new HashedObj(
                metricDTO.getContent(), "name", HashedObj.Type.STRICT, 2));

        return ResponseEntity.ok(save);
    }

    @GetMapping(value = "/hashedObj/{id}")
    public ResponseEntity<HashedObj> getHashedObj(@PathVariable String id) {

        Optional<HashedObj> hashedObj = hashedObjRepository.findById(id);
        return hashedObj.map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }


}
