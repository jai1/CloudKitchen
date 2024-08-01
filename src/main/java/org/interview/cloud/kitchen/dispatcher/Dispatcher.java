package org.interview.cloud.kitchen.dispatcher;

import org.interview.cloud.kitchen.data.Order;

public interface Dispatcher {
    void courierArrived(Order order);

    void orderPrepared(Order order);
}
