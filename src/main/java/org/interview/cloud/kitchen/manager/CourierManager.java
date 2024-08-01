package org.interview.cloud.kitchen.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.data.Courier;
import org.interview.cloud.kitchen.data.Order;
import org.interview.cloud.kitchen.data.RawOrder;
import org.interview.cloud.kitchen.dispatcher.Dispatcher;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


@Singleton
public class CourierManager {
    private final AppProperties appProperties;

    private final Dispatcher dispatcher;

    private final ScheduledExecutorService scheduledExecutorService;

    private final AtomicLong idGenerator;

    private final Random rand;

    @Inject
    public CourierManager(AppProperties appProperties, Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.appProperties = appProperties;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(appProperties.getCourierThreadCount());
        this.idGenerator = new AtomicLong();
        this.rand = new Random();
    }
    public void dispatch(Order order) {
        this.scheduledExecutorService.schedule(() -> dispatcher.courierArrived(order), order.getCourier().getDelayTimeInMillis(), TimeUnit.MILLISECONDS);
    }

    public Courier createCourier() {
        return new Courier(idGenerator.getAndIncrement(), rand.nextLong(appProperties.getMinCourierWaitTimeInMillis(), appProperties.getMaxCourierWaitTimeInMillis() + 1));
    }

    public void shutdown() throws InterruptedException {
        this.scheduledExecutorService.shutdown();
        this.scheduledExecutorService.awaitTermination(appProperties.getShutDownWaitTimeInSeconds(), TimeUnit.SECONDS);
    }
}
