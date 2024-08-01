package org.interview.cloud.kitchen.modules;

import com.google.inject.AbstractModule;
import org.interview.cloud.kitchen.dispatcher.Dispatcher;
import org.interview.cloud.kitchen.dispatcher.FifoDispatcher;

public class DispatcherModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Dispatcher.class).to(FifoDispatcher.class);
    }
}
