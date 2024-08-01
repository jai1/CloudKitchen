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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Singleton
public class MatchDispatcher implements Dispatcher {

    @Inject
    StatsManager statsManager;

    // TODO: data wasn't required
    ConcurrentHashMap<String, Long> ordersReceivedOrDispatched = new ConcurrentHashMap();

    public void courierArrived(Order order) {
        log.debug("Courier Arrived {}", order);
        Long enqueueTimeInMillis = ordersReceivedOrDispatched.putIfAbsent(order.getRawOrder().getId(), System.currentTimeMillis());
        if (enqueueTimeInMillis != null) {
            // Entry already present - order fulfilled
            log.debug("Order fulfilled {}", order);
            statsManager.reportOrder(enqueueTimeInMillis);
            ordersReceivedOrDispatched.remove(order.getRawOrder().getId());
        }
    }

    // TODO: Can be refactored for a single function call.
    public void orderPrepared(Order order) {
        log.debug("Order Ready {}", order);
        Long enqueueTimeInMillis =  ordersReceivedOrDispatched.putIfAbsent(order.getRawOrder().getId(), System.currentTimeMillis());
        if (enqueueTimeInMillis != null) {
            log.debug("Order fulfilled {}", order);
            statsManager.reportCourier(enqueueTimeInMillis);
            ordersReceivedOrDispatched.remove(order.getRawOrder().getId());
        }
    }
}
