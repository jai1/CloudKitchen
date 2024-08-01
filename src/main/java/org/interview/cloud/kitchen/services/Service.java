package org.interview.cloud.kitchen.services;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.simulators.Simulator;

@Slf4j
public class Service {

    @Inject
    private AppProperties appProperties;

    @Inject
    Simulator simulator;

    public void run() {
        log.debug(appProperties.toString());
        simulator.run();
    }
}
