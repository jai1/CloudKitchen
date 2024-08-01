package org.interview.cloud.kitchen.stats;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Singleton
public class StatsManager {
    AtomicLong orderCount = new AtomicLong();

    AtomicLong orderWaitTimeInMillis = new AtomicLong();

    AtomicLong courierCount = new AtomicLong();

    AtomicLong courierWaitTimeInMillis = new AtomicLong();

    public void reportOrder(long enqueueTimeInMillis) {
        orderCount.getAndIncrement();
        courierCount.getAndIncrement();
        orderWaitTimeInMillis.getAndAdd(System.currentTimeMillis() - enqueueTimeInMillis);
    }

    public void reportCourier(long enqueueTimeInMillis) {
        orderCount.getAndIncrement();
        courierCount.getAndIncrement();
        courierWaitTimeInMillis.getAndAdd(System.currentTimeMillis() - enqueueTimeInMillis);
    }

    public void printReport() {
        log.info("orderCount = {}, orderWaitTimeInMillis = {}, courierCount = {}, courierWaitTimeInMillis = {}",
                orderCount.get(), orderWaitTimeInMillis.get(), courierCount.get(), courierWaitTimeInMillis.get());
        log.info("Average order waitTime in Millis = {}", orderWaitTimeInMillis.get() / orderCount.get());
        log.info("Average courier waitTime in Millis = {}", courierWaitTimeInMillis.get() / courierCount.get());
    }
}
