package org.interview.cloud.kitchen.manager;

import org.interview.cloud.kitchen.data.Courier;
import org.interview.cloud.kitchen.data.Order;
import org.interview.cloud.kitchen.data.RawOrder;
import org.interview.cloud.kitchen.stats.StatsManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderManagerTest {

    @Mock
    private KitchenManager kitchenManagerMock;

    @Mock
    private CourierManager courierManagerMock;

    @Mock
    private StatsManager statsManagerMock;

    @InjectMocks
    private OrderManager orderManager;

    private final RawOrder rawOrder = new RawOrder();
    private final Courier expectedCourier = new Courier(10L, 10L);

    @Test
    public void testStartPrepping_CreatesOrderAndCallsMethods() {
        // Mock courierManager behavior
        Mockito.when(courierManagerMock.createCourier()).thenReturn(expectedCourier);

        orderManager.startPrepping(rawOrder);

        // Verify method calls on mocks
        Mockito.verify(courierManagerMock, Mockito.times(1)).createCourier();
        Mockito.verify(kitchenManagerMock, Mockito.times(1)).prepare(Mockito.any(Order.class));
        Mockito.verify(courierManagerMock, Mockito.times(1)).dispatch(Mockito.any(Order.class));
    }

    @Test
    public void testStartPrepping_CreatesOrderWithExpectedCourier() {
        // Mock courierManager behavior
        Mockito.when(courierManagerMock.createCourier()).thenReturn(expectedCourier);

        orderManager.startPrepping(rawOrder);

        Mockito.verify(kitchenManagerMock, Mockito.times(1)).prepare(Mockito.any(Order.class));
    }

    @Test
    public void testShutDown_CallsStatsAndManagerMethods() throws InterruptedException {
        orderManager.shutDown();

        // Verify method calls on mocks
        Mockito.verify(statsManagerMock, Mockito.times(1)).printReport();
        Mockito.verify(kitchenManagerMock, Mockito.times(1)).shutdown();
        Mockito.verify(courierManagerMock, Mockito.times(1)).shutdown();
    }
}