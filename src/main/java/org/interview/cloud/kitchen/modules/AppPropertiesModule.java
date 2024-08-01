package org.interview.cloud.kitchen.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertiesModule extends AbstractModule {

    private static final Logger logger = LogManager.getLogger(AppPropertiesModule.class);

    @Override
    protected void configure() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            logger.error("IOException occurred while loading application.properties", ex);
        }

        Names.bindProperties(binder(), properties);
        install(new DispatcherModule(properties));
    }
}