package com.andersen.demo.service;

import com.andersen.demo.model.Metric;
import com.andersen.demo.model.MetricType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MetricsService {

    /**
     * Method to get metrics for user by metrics types
     *
     * @param userId      user id
     * @param metricTypes metrics types
     * @param pageable    page settings
     * @return metrics page
     */
    Page<Metric> getMetricsByUserIdAndMetricsTypes(Long userId, List<MetricType> metricTypes, Pageable pageable);

    /**
     * Store metric
     *
     * @param metric metric @see Metric.class
     * @return stored metric
     */
    Metric addMetric(Metric metric);
}
