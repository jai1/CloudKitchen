package org.interview.cloud.kitchen.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Courier {
    long id;
    long delayTimeInMillis;
}
