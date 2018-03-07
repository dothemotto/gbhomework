package com.globalblue.controllers;

import com.globalblue.commons.AbstractController;
import com.globalblue.commons.Constants;
import com.globalblue.commons.ControllerFactory;
import com.globalblue.services.CSVService;
import com.globalblue.services.DataGenerationService;
import com.globalblue.services.ValidatorService;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.inject.Inject;
import java.util.Objects;

public class RootController extends AbstractController {

    private final ControllerFactory controllerFactory;
    private final CSVService csvService;
    private final DataGenerationService dataGenerationService;
    private final ValidatorService validatorService;

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
    public RootController(final ControllerFactory controllerFactory, final CSVService csvService, final DataGenerationService dataGenerationService, final ValidatorService validatorService) {
        this.controllerFactory = Objects.requireNonNull(controllerFactory, "Controller factory has not been instantiated! Please check that!");
        this.csvService = Objects.requireNonNull(csvService, "CSV service has not been instantiated! Please check that!");
        this.dataGenerationService = Objects.requireNonNull(dataGenerationService, "Data generation service has not been instantiated! Please check that!");
        this.validatorService = Objects.requireNonNull(validatorService, "Validator service has not been instantiated! Please check that!");
    }

    @FXML
    public void enter() {


    }

    @FXML
    public void dateFromAction() {
        if (!validatorService.isDateInRange(dateFrom.getValue())) {
            getErrorDialog().show();
            dateFrom.setValue(null);
        }
    }

    @FXML
    public void dateToAction() {
        if (!validatorService.isDateInRange(dateTo.getValue())) {
            System.out.println("err");
            getErrorDialog().show();
            dateTo.setValue(null);
        } else {
            if (!validatorService.isValidToDate(dateFrom.getValue(), dateTo.getValue())) {
                getErrorDialog().show();
                dateTo.setValue(null);
            }
        }

        filename.setText(generateFilename());
    }

    private Stage getErrorDialog() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        Text txt = new Text("Error");
        txt.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.getChildren().add(txt);
        Scene dialogScene = new Scene(dialogVbox, 350, 150);
        dialog.setScene(dialogScene);
        dialog.initStyle(StageStyle.UTILITY);

        return dialog;
    }

    private String generateFilename() {
        StringBuilder sb = new StringBuilder();

        sb.append(Constants.FILENAME_PREFIX)
                .append(shopNumber.getText())
                .append(Constants.UNDERSCORE)
                .append(dateFrom.getValue().toString())
                .append(Constants.UNDERSCORE)
                .append(dateTo.getValue().toString())
                .append(Constants.CSV_EXTENSION);

        return sb.toString();
    }

}
