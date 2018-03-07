package com.globalblue.modules;

import com.google.inject.AbstractModule;

public class AppBundleModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ControllerFactoryModule());
        install(new CSVServiceModule());
    }
}
