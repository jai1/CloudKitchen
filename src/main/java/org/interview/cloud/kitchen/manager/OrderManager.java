package org.interview.cloud.kitchen.manager;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.interview.cloud.kitchen.data.Courier;
import org.interview.cloud.kitchen.data.Order;
import org.interview.cloud.kitchen.data.RawOrder;
import org.interview.cloud.kitchen.dispatcher.Dispatcher;
import org.interview.cloud.kitchen.stats.StatsManager;

@Slf4j
@Singleton
public class OrderManager {

    @Inject
    KitchenManager kitchenManager;

    @Inject
    CourierManager courierManager;

    @Inject
    StatsManager statsManager;

    public void startPrepping(RawOrder rawOrder) {
        log.debug("Received {}", rawOrder);
        Courier courier = courierManager.createCourier();
        Order order = new Order(rawOrder, courier);
        kitchenManager.prepare(order);
        courierManager.dispatch(order);
    }

    public void shutDown() throws InterruptedException {
        kitchenManager.shutdown();
        courierManager.shutdown();
        statsManager.printReport();
    }
}
