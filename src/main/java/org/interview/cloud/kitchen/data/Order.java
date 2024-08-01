package org.interview.cloud.kitchen.data;

import lombok.Data;

@Data
public class Order {
    private String id;
    private String name;
    private String temp;
    private int shelfLife;
    private double decayRate;
}