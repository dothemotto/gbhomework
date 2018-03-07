package com.globalblue.modules;

import com.globalblue.services.ValidatorService;
import com.globalblue.services.impl.ValidatorServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ValidatorServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ValidatorService.class).to(ValidatorServiceImpl.class).in(Singleton.class);
    }
}
