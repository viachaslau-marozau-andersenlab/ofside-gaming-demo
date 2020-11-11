package com.andersen.demo.mapper;

import com.andersen.demo.dto.MetricDto;
import com.andersen.demo.model.Metric;
import com.andersen.demo.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {UserService.class})
public interface MetricMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", source = "metricDto.userId")
    })
    Metric fromDto(MetricDto metricDto);

    @Mappings({
            @Mapping(target = "userId", source = "metric.user.id")
    })
    MetricDto toDto(Metric metric);
}
