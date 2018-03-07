package com.globalblue.modules;

import com.globalblue.services.DataGenerationService;
import com.globalblue.services.impl.DataGenerationServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class DataGenerationServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DataGenerationService.class).to(DataGenerationServiceImpl.class).in(Singleton.class);
    }
}
