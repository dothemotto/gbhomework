package com.globalblue.controllers;

import com.globalblue.commons.AbstractController;
import com.globalblue.commons.ControllerFactory;
import com.globalblue.services.CSVService;
import com.globalblue.services.DataGenerationService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.inject.Inject;
import java.util.Objects;

public class RootController extends AbstractController {

    private final ControllerFactory controllerFactory;
    private final CSVService csvService;
    private final DataGenerationService dataGenerationService;

    @FXML
    private TextField shopNumber;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private TextField filename;
    @FXML
    private TextField executionCommand;

    @Inject
    public RootController(final ControllerFactory controllerFactory, final CSVService csvService, final DataGenerationService dataGenerationService) {
        this.controllerFactory = Objects.requireNonNull(controllerFactory, "Controller factory has not been instantiated! Please check that!");
        this.csvService = Objects.requireNonNull(csvService, "CSV service has not been instantiated! Please check that!");
        this.dataGenerationService = Objects.requireNonNull(dataGenerationService, "Data generation service has not been instantiated! Please check that!");
    }



}
