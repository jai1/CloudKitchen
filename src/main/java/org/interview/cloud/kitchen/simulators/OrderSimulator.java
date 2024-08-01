package org.interview.cloud.kitchen.simulators;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.data.RawOrder;
import org.interview.cloud.kitchen.manager.OrderManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Singleton
public class OrderSimulator implements Simulator {

    private final ObjectMapper objectMapper;

    private final AppProperties appProperties;

    private final OrderManager orderManager;

    private final AtomicInteger currentIndex;

    private final ScheduledExecutorService scheduledExecutorService;

    private boolean shutdown;

    @Inject
    public OrderSimulator(ObjectMapper objectMapper, AppProperties appProperties, OrderManager orderManager) {
        this.objectMapper = objectMapper;
        this.appProperties = appProperties;
        this.orderManager = orderManager;
        this.currentIndex = new AtomicInteger(0);
        this.scheduledExecutorService = Executors.newScheduledThreadPool(appProperties.getSimulatorThreadCount());
        this.shutdown = false;
    }

    public void run() {
        List<RawOrder> rawOrders = new CopyOnWriteArrayList<RawOrder>(getKitchenOrders());
        scheduledExecutorService.scheduleAtFixedRate(() -> simulateOrder(rawOrders), 0, 1, TimeUnit.SECONDS);
    }

    public synchronized void shutdown() {
        if (shutdown) {
            return;
        }
        shutdown = true;
        scheduledExecutorService.shutdown();
        orderManager.shutDown();
    }

    private void simulateOrder(List<RawOrder> rawOrders) {
        for (int i = 0; i<appProperties.getOrdersPerSecond(); i++) {
            int index = currentIndex.getAndIncrement();
            if (index >= rawOrders.size()) {
                shutdown();
            }
            orderManager.startPrepping(rawOrders.get(i));
            if (index == rawOrders.size() - 1) {
                shutdown();
            }
        }
    }



    private List<RawOrder> getKitchenOrders() {
        final String filePath = appProperties.getFilePath();
        try {
             return objectMapper.readValue(new File(filePath), new TypeReference<>() {});
        } catch (IOException e) {
            log.error("Failed to read orders from file: {}", filePath, e);
            shutdown();
            return null;
        }
    }


}
