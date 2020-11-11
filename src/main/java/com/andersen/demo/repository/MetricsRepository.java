package com.andersen.demo.repository;

import com.andersen.demo.model.Metric;
import com.andersen.demo.model.MetricType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public interface MetricsRepository extends CrudRepository<Metric, Long> {

    Page<Metric> findAllByUserIdAndMetricTypeIn(Long userId, List<MetricType> metricTypes, Pageable pageable);

    Optional<Metric> findFirstByUserIdAndMetricTypeOrderByCreatedDateDesc(Long userId, MetricType metricTypes);
}
