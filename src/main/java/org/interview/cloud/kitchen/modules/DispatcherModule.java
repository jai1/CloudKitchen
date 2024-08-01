package org.interview.cloud.kitchen.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import lombok.SneakyThrows;
import org.interview.cloud.kitchen.configs.AppProperties;
import org.interview.cloud.kitchen.dispatcher.Dispatcher;
import org.interview.cloud.kitchen.dispatcher.FifoDispatcher;

import java.util.Properties;

public class DispatcherModule extends AbstractModule {
    private final Properties properties;

    public DispatcherModule(Properties properties) {
        this.properties = properties;
    }

    @SneakyThrows
    @Override
    protected void configure() {
        String className = properties.getProperty("dispatchStrategyClassName");
        Class<?> clazz = Class.forName(className);
        bind(Dispatcher.class).to((Class<? extends Dispatcher>) clazz);
    }
}
