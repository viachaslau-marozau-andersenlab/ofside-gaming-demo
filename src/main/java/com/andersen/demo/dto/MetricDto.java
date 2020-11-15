package com.andersen.demo.dto;

import com.andersen.demo.model.MetricType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetricDto {

    @ApiModelProperty(hidden = true)
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "User id is mandatory.")
    @Min(message = "User id must be more than 0", value = 0)
    @JsonProperty("userId")
    private Long userId;

    @NotNull(message = "Metric value is mandatory.")
    @JsonProperty("metricValue")
    private Double metricValue;

    @JsonProperty("metricType")
    private MetricType metricType;

    @ApiModelProperty(hidden = true)
    @JsonProperty("createdDate")
    private LocalDateTime createdDate;
}
