package com.andersen.demo.service;

import com.andersen.demo.model.Metric;
import com.andersen.demo.model.MetricType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MetricsService {

    Page<Metric> getMetricsByUserIdAndMetricsTypes(Long userId, List<MetricType> metricTypes, Pageable pagable);

    Metric addMetric(Metric metric);
}
