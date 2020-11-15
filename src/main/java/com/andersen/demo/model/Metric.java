package com.andersen.demo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "demo_metrics")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "metric_value")
    private Double metricValue;

    @NotNull
    @Column(name = "metric_type")
    @Enumerated(EnumType.STRING)
    private MetricType metricType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
}
