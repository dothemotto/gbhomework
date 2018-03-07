package com.globalblue.controllers;

import com.globalblue.commons.AbstractController;
import com.globalblue.commons.ControllerFactory;
import com.globalblue.services.CSVService;

import javax.inject.Inject;
import java.util.Objects;

public class RootController extends AbstractController {

    private final ControllerFactory controllerFactory;
    private final CSVService csvService;

    @Inject
    public RootController(final ControllerFactory controllerFactory, final CSVService csvService) {
        this.controllerFactory = Objects.requireNonNull(controllerFactory, "Controller factory has not been instantiated! Please check that!");
        this.csvService = Objects.requireNonNull(csvService, "CSV service has not been instantiated! Please check that!");
    }
}
