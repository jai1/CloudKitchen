package org.interview.cloud.kitchen.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.data.Order;
import org.interview.cloud.kitchen.dispatcher.Dispatcher;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class KitchenManager {
    private final AppProperties appProperties;

    private final Dispatcher dispatcher;

    private final ScheduledExecutorService scheduledExecutorService;

    @Inject
    public KitchenManager(AppProperties appProperties, Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.appProperties = appProperties;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(appProperties.getKitchenThreadCount());
    }

    public void prepare(Order order) {
        this.scheduledExecutorService.schedule(() -> dispatcher.orderPrepared(order), order.getRawOrder().getPrepTime(), TimeUnit.SECONDS);
    }

    public void shutdown() throws InterruptedException {
        this.scheduledExecutorService.shutdown();
        this.scheduledExecutorService.awaitTermination(appProperties.getShutDownWaitTimeInSeconds(), TimeUnit.SECONDS);
    }
}
