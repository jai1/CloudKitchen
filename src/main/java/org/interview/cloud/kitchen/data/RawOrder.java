package org.interview.cloud.kitchen.data;

import lombok.Data;

@Data
public class RawOrder {
    private String id;
    private String name;
    private long prepTime;
}