package org.interview.cloud.kitchen.services;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.processors.OrderProcessor;

@Slf4j
public class Service {

    @Inject
    private AppProperties appProperties;

    @Inject
    OrderProcessor orderProcessor;

    public void run() {
        log.debug(appProperties.toString());
        orderProcessor.processOrders();
    }
}
