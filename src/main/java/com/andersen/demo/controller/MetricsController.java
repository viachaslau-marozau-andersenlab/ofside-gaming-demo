package com.andersen.demo.controller;

import com.andersen.demo.dto.MetricDto;
import com.andersen.demo.mapper.MetricMapper;
import com.andersen.demo.model.Metric;
import com.andersen.demo.model.MetricType;
import com.andersen.demo.service.MetricsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/v1/metrics")
@RequiredArgsConstructor
@CrossOrigin
@Validated
public class MetricsController {

    private final MetricsService metricsService;
    private final MetricMapper metricMapper;

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get metrics by user id and metric type")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully get metrics"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Page<MetricDto> getMetricsByUserIdAndMetricsTypes(
            @ApiParam(value = "User id") @NotNull @PathVariable("userId") Long userId,
            @ApiParam(value = "Metrics types") @RequestParam(value = "metricTypes", defaultValue = "COLD_WATER, HOT_WATER, GAS") List<MetricType> metricTypes,
            @ApiParam(value = "Page") @RequestParam(value = "page", defaultValue = "0") int page,
            @ApiParam(value = "Limit") @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Page<Metric> metricsPage = metricsService.getMetricsByUserIdAndMetricsTypes(userId, metricTypes, PageRequest.of(page, size));
        return new PageImpl<>(metricsPage.get()
                .map(metricMapper::toDto)
                .collect(toList()),
                PageRequest.of(page, size),
                metricsPage.getTotalElements());
    }

    @PostMapping
    @ApiOperation(value = "Add metric")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Metric successfully added"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    })
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public MetricDto addMetric(
            @ApiParam(value = "Metric value to add") @RequestBody MetricDto metricDto
    ) {
        Metric metric = metricsService.addMetric(metricMapper.fromDto(metricDto));
        return metricMapper.toDto(metric);
    }
}
