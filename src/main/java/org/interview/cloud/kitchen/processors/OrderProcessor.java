package org.interview.cloud.kitchen.processors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.data.Order;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class OrderProcessor {

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private AppProperties appProperties;

    public void processOrders() {
        final String filePath = appProperties.getFilePath();
        try {
            List<Order> orders = objectMapper.readValue(new File(filePath), new TypeReference<List<Order>>() {});
            for (Order order : orders) {
                log.info("Order: {}", order);
            }
        } catch (IOException e) {
            log.error("Failed to read orders from file: {}", filePath, e);
        }
    }


}
