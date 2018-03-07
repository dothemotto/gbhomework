package com.globalblue.modules;

import com.globalblue.services.CSVService;
import com.globalblue.services.impl.CSVServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CSVServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CSVService.class).to(CSVServiceImpl.class).in(Singleton.class);
    }
}
