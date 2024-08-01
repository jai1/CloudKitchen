package org.interview.cloud.kitchen.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.interview.cloud.kitchen.data.Courier;
import org.interview.cloud.kitchen.data.Order;
import org.interview.cloud.kitchen.data.RawOrder;
import org.interview.cloud.kitchen.stats.StatsManager;

import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Singleton
public class FifoDispatcher implements Dispatcher {

    @Inject
    StatsManager statsManager;

    @Data
    @AllArgsConstructor
    class Entry< T > {
        long enqueueTimeInMillis;
        T data;
    }

    // TODO: data wasn't required
    ConcurrentLinkedQueue<Entry<Courier>> waitingCouriers = new ConcurrentLinkedQueue<>();

    ConcurrentLinkedQueue<Entry<RawOrder>> readyOrders = new ConcurrentLinkedQueue<>();
    public synchronized void courierArrived(Order order) {
        log.debug("Courier Arrived {}", order);
        Entry<RawOrder> rawOrder = readyOrders.poll();
        if (rawOrder != null) {
            log.debug("Order fulfilled {}", order);
            statsManager.reportOrder(rawOrder.enqueueTimeInMillis);
        } else {
            log.debug("WaitingCouriers Enqueue Success {}", waitingCouriers.add(new Entry<>(System.currentTimeMillis(), order.getCourier())));
        }
    }

    public synchronized void orderPrepared(Order order) {
        log.debug("Order Ready {}", order);
        Entry<Courier> courier = waitingCouriers.poll();
        if (courier != null) {
            log.debug("Order fulfilled {}", order);
            statsManager.reportCourier(courier.enqueueTimeInMillis);
        } else {
            log.debug("ReadyOrders Enqueue Success {}", readyOrders.add(new Entry<>(System.currentTimeMillis(), order.getRawOrder())));
        }
    }
}
