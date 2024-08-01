package org.interview.cloud.kitchen.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.manager.OrderManager;
import org.interview.cloud.kitchen.simulators.OrderSimulator;
import org.interview.cloud.kitchen.simulators.Simulator;

public class SimulatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Simulator.class).to(OrderSimulator.class);
    }
}
