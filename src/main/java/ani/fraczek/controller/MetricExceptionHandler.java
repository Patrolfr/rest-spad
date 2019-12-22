package ani.fraczek.controller;

import ani.fraczek.service.MetricException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MetricExceptionHandler {

    @ExceptionHandler(MetricException.class)
    public ResponseEntity<String> handleMetricException(MetricException metricException){
        return new ResponseEntity<>(metricException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
