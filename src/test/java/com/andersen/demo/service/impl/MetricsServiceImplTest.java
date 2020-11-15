package com.andersen.demo.service.impl;

import com.andersen.demo.model.Metric;
import com.andersen.demo.model.User;
import com.andersen.demo.repository.MetricsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

import static com.andersen.demo.model.MetricType.COLD_WATER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MetricsServiceImplTest {

    @Mock
    private MetricsRepository metricsRepository;

    @InjectMocks
    MetricsServiceImpl metricsService;

    @Before
    public void init() {
        initMocks(this);
    }

    @After
    public void destroy() {
        clearInvocations(metricsRepository);
    }

    @Test
    public void addMetric_NoResultsBefore() {
        when(metricsRepository.findFirstByUserIdAndMetricTypeOrderByCreatedDateDesc(eq(1000L), eq(COLD_WATER)))
                .thenReturn(Optional.empty());
        when(metricsRepository.save(any())).thenAnswer((Answer<Metric>) invocation -> {
            Object[] args = invocation.getArguments();
            return (Metric) args[0];
        });

        Metric result = metricsService.addMetric(
                Metric.builder()
                        .metricValue(100D)
                        .metricType(COLD_WATER)
                        .user(User.builder()
                                .id(1000L)
                                .login("user")
                                .build())
                        .build()
        );

        verify(metricsRepository, times(1))
                .save(any());

        assertEquals(100D, result.getMetricValue());
        assertEquals(COLD_WATER, result.getMetricType());
    }

    @Test
    public void addMetric_CorrectValue() {
        when(metricsRepository.findFirstByUserIdAndMetricTypeOrderByCreatedDateDesc(eq(1000L), eq(COLD_WATER)))
                .thenReturn(Optional.of(
                        Metric.builder()
                                .metricValue(50D)
                                .metricType(COLD_WATER)
                                .user(User.builder()
                                        .id(1000L)
                                        .login("user")
                                        .build())
                                .build()));
        when(metricsRepository.save(any())).thenAnswer((Answer<Metric>) invocation -> {
            Object[] args = invocation.getArguments();
            return (Metric) args[0];
        });

        Metric result = metricsService.addMetric(
                Metric.builder()
                        .metricValue(100D)
                        .metricType(COLD_WATER)
                        .user(User.builder()
                                .id(1000L)
                                .login("user")
                                .build())
                        .build()
        );

        verify(metricsRepository, times(1))
                .save(any());

        assertEquals(100D, result.getMetricValue());
        assertEquals(COLD_WATER, result.getMetricType());
    }

    @Test
    public void addMetric_TheSameValue() {
        when(metricsRepository.findFirstByUserIdAndMetricTypeOrderByCreatedDateDesc(eq(1000L), eq(COLD_WATER)))
                .thenReturn(Optional.of(
                        Metric.builder()
                                .metricValue(100D)
                                .metricType(COLD_WATER)
                                .user(User.builder()
                                        .id(1000L)
                                        .login("user")
                                        .build())
                                .build()));
        when(metricsRepository.save(any())).thenAnswer((Answer<Metric>) invocation -> {
            Object[] args = invocation.getArguments();
            return (Metric) args[0];
        });

        Metric result = metricsService.addMetric(
                Metric.builder()
                        .metricValue(100D)
                        .metricType(COLD_WATER)
                        .user(User.builder()
                                .id(1000L)
                                .login("user")
                                .build())
                        .build()
        );

        verify(metricsRepository, times(1))
                .save(any());

        assertEquals(100D, result.getMetricValue());
        assertEquals(COLD_WATER, result.getMetricType());
    }

    @Test(expected = ResponseStatusException.class)
    public void addMetric_IncorrectValue() {
        when(metricsRepository.findFirstByUserIdAndMetricTypeOrderByCreatedDateDesc(eq(1000L), eq(COLD_WATER)))
                .thenReturn(Optional.of(
                        Metric.builder()
                                .metricValue(500D)
                                .metricType(COLD_WATER)
                                .user(User.builder()
                                        .id(1000L)
                                        .login("user")
                                        .build())
                                .build()));

        metricsService.addMetric(
                Metric.builder()
                        .metricValue(100D)
                        .metricType(COLD_WATER)
                        .user(User.builder()
                                .id(1000L)
                                .login("user")
                                .build())
                        .build()
        );
    }

    @Test
    public void getMetricsByUserIdAndMetricsTypes() {
        when(metricsRepository.findAllByUserIdAndMetricTypeIn(eq(1000L), anyList(), any()))
                .thenReturn(new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 1), 0));

        metricsService.getMetricsByUserIdAndMetricsTypes(1000L, Collections.emptyList(), PageRequest.of(0, 1));
        verify(metricsRepository, times(1))
                .findAllByUserIdAndMetricTypeIn(eq(1000L), anyList(), any());
    }
}
