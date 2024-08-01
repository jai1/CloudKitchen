package org.interview.cloud.kitchen.configs;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

//    @Inject
//    @Name("courierThreadCount")
//    private int courierThreadCount = 4;

//    @Inject
//    @Name("kitchenThreadCount")
//    private int kitchenThreadCount = 4;
//
//    @Inject
//    @Named("minCourierWaitTimeInMillis")
//    private long minCourierWaitTimeInMillis = 3000;
//
//    @Inject
//    @Named("maxCourierWaitTimeInMillis")
//    private long maxCourierWaitTimeInMillis = 15000;
}