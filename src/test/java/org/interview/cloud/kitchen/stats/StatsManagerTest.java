package org.interview.cloud.kitchen.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

public class StatsManagerTest {

    @InjectMocks
    private StatsManager statsManager;

    @BeforeEach
    void setUp() {
        statsManager = new StatsManager();
    }

    @Test
    void testReportOrder() {
        long enqueueTimeInMillis = System.currentTimeMillis() - 1000; // simulate an order enqueued 1 second ago

        statsManager.reportOrder(enqueueTimeInMillis);

        assertEquals(1, statsManager.orderCount.get());
        assertEquals(1, statsManager.courierCount.get());
        assertEquals(1000, statsManager.orderWaitTimeInMillis.get(), 50);
    }

    @Test
    void testReportCourier() {
        long enqueueTimeInMillis = System.currentTimeMillis() - 2000; // simulate a courier enqueued 2 seconds ago

        statsManager.reportCourier(enqueueTimeInMillis);

        assertEquals(1, statsManager.orderCount.get());
        assertEquals(1, statsManager.courierCount.get());
        assertEquals(2000, statsManager.courierWaitTimeInMillis.get(), 50);
    }
}