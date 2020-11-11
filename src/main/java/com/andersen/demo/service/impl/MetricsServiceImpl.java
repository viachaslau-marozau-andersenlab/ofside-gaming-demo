package com.andersen.demo.service.impl;

import com.andersen.demo.model.Metric;
import com.andersen.demo.model.MetricType;
import com.andersen.demo.repository.MetricsRepository;
import com.andersen.demo.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsServiceImpl implements MetricsService {

    private final MetricsRepository repository;

    @Override
    public Page<Metric> getMetricsByUserIdAndMetricsTypes(Long userId, List<MetricType> metricTypes, Pageable pageable) {
        log.info("Start getMetricsByUserIdAndMetricsTypes() with user id: {}, metricTypes: {}", userId, metricTypes);
        return repository.findAllByUserIdAndMetricTypeIn(userId, metricTypes, pageable);
    }

    @Override
    public Metric addMetric(Metric metric) {
        log.info("Start addMetric() with user id: {}, metricValue: {},  metricType: {}", metric.getUser().getId(), metric.getMetricValue(), metric.getMetricType());
        verifyMetric(metric);
        metric.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return repository.save(metric);
    }

    private void verifyMetric(Metric metricToAdd) {
        Optional<Metric> lastExistingMetric = repository.findFirstByUserIdAndMetricTypeOrderByCreatedDateDesc(metricToAdd.getUser().getId(), metricToAdd.getMetricType());
        if (lastExistingMetric.isPresent()
                && metricToAdd.getMetricValue() < lastExistingMetric.get().getMetricValue()) {
            log.error("Current metric value  must be more that previous. Current value {}, previous value {}, user id: {},  metricType: {}",
                    metricToAdd.getMetricValue(),
                    lastExistingMetric.get().getMetricValue(),
                    metricToAdd.getUser().getId(),
                    metricToAdd.getMetricType());

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current metric value must be more that previous");
        }
    }
}
