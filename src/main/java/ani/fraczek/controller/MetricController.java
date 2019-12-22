package ani.fraczek.controller;

import ani.fraczek.domain.dto.MetricDTO;
import ani.fraczek.domain.entity.metrics.SpadMetric;
import ani.fraczek.metric.HashedObj;
import ani.fraczek.metric.StudentRepository;
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

    private final StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity newMetric(@RequestBody MetricDTO metricDTO) {
//        SpadMetric save = metricRepository.save(SpadMetric.builder()
//                .content(metricDTO.getContent())
//                .sessionUserLogin(userService.getCurrentUserLogin())
//                .chunkSequence(metricDTO.getChunkSequence())
//                .uuid(UUID.randomUUID().toString())
//                .build());
        SpadMetric save = metricService.addMetric(metricDTO);
        return ResponseEntity.ok(save);
    }

    @PostMapping(value = "/student")
    public ResponseEntity newStudent(@RequestBody MetricDTO metricDTO) {

        HashedObj hashedObj = new HashedObj(
                metricDTO.getContent(), "Johny", HashedObj.Type.STRICT, 1);
        HashedObj save = studentRepository.save(hashedObj);

//        TODO
        return ResponseEntity.ok(save);
    }

    @GetMapping(value = "/student/{id}")
    public ResponseEntity<HashedObj> getStudent(@PathVariable String id) {

        Optional<HashedObj> byId = studentRepository.findById(id);

        return ResponseEntity.ok(byId.get());
    }


}
