package org.interview.cloud.kitchen;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import org.interview.cloud.kitchen.modules.AppPropertiesModule;
import org.interview.cloud.kitchen.modules.DispatcherModule;
import org.interview.cloud.kitchen.modules.JsonModule;
import org.interview.cloud.kitchen.modules.SimulatorModule;
import org.interview.cloud.kitchen.services.Service;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Staring Main !!!");
        Injector injector = Guice.createInjector(new AppPropertiesModule(),
                new JsonModule(),
                new SimulatorModule(),
                new DispatcherModule());
        Service service = injector.getInstance(Service.class);
        service.run();
    }
}

