package org.interview.cloud.kitchen.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private RawOrder rawOrder;
    private Courier courier;
}