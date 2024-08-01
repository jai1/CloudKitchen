package org.interview.cloud.kitchen.configs;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AppProperties {
    @Inject
    @Named("threadCount")
    private int threadCount;

    @Inject
    @Named("milliSecondsWait")
    private long milliSecondsWait;

    @Inject
    @Named("filePath")
    private String filePath;

    @Inject
    @Named("simulatorThreadCount")
    private int simulatorThreadCount;

    @Inject
    @Named("ordersPerSecond")
    private int ordersPerSecond;

    @Inject
    @Named("courierThreadCount")
    private int courierThreadCount;

    @Inject
    @Named("kitchenThreadCount")
    private int kitchenThreadCount;

    @Inject
    @Named("minCourierWaitTimeInMillis")
    private long minCourierWaitTimeInMillis;

    @Inject
    @Named("maxCourierWaitTimeInMillis")
    private long maxCourierWaitTimeInMillis;

    @Inject
    @Named("shutDownWaitTimeInSeconds")
    private long shutDownWaitTimeInSeconds;
}