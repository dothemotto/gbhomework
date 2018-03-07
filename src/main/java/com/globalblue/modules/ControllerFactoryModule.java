package com.globalblue.modules;

import com.globalblue.commons.ControllerFactory;
import com.globalblue.commons.impl.ControllerFactoryImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ControllerFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ControllerFactory.class).to(ControllerFactoryImpl.class).in(Singleton.class);
    }
}
