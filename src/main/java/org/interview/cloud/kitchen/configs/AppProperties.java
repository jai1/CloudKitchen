package org.interview.cloud.kitchen.configs;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AppProperties {
    @Inject
    @Named("threadCount")
    private int threadCount;

    @Inject
    @Named("milliSecondsWait")
    private long milliSecondsWait;

    @Inject
    @Named("filePath")
    private String filePath;
}