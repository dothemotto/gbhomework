package com.globalblue.controllers;

import com.globalblue.commons.AbstractController;
import com.globalblue.commons.Constants;
import com.globalblue.models.Invoice;
import com.globalblue.services.CSVService;
import com.globalblue.services.DataGenerationService;
import com.globalblue.services.ValidatorService;
import com.globalblue.utils.GBUtils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javax.inject.Inject;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MainController extends AbstractController implements Initializable {

    private final CSVService csvService;
    private final DataGenerationService dataGenerationService;
    private final ValidatorService validatorService;
    private final HashMap<Integer, ObservableList<Invoice>> data;

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
    public MainController(final CSVService csvService, final DataGenerationService dataGenerationService, final ValidatorService validatorService) {
        this.csvService = Objects.requireNonNull(csvService, "CSV service has not been instantiated! Please check that!");
        this.dataGenerationService = Objects.requireNonNull(dataGenerationService, "Data generation service has not been instantiated! Please check that!");
        this.validatorService = Objects.requireNonNull(validatorService, "Validator service has not been instantiated! Please check that!");

        this.data = dataGenerationService.generateRecords(5, 100000);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopNumber.setOnKeyReleased((event) ->
                filename.setText(GBUtils.generateFilename(shopNumber, dateFrom, dateTo))
        );

        executionCommand.setOnKeyReleased((event) -> {
            if (KeyCode.ENTER.equals(event.getCode())
                    && "Y".equals(executionCommand.getText())) {

                if (validatorService.isFieldNotEmpty(shopNumber)
                        && validatorService.isFieldNotEmpty(dateFrom)
                        && validatorService.isFieldNotEmpty(dateTo)
                        && validatorService.isFieldNotEmpty(filename)) {
                    int invoicesCount = 0;
                    ObservableList<Invoice> invoicesForShop = data.get(Integer.parseInt(shopNumber.getText()));

                    try {
                        FileWriter writer = new FileWriter(filename.getText());

                        csvService.writeLine(writer, Constants.HEADERS);

                        List<Invoice> filtered = invoicesForShop
                                .stream()
                                .filter(invoice ->
                                        (dateTo.getValue().isAfter(invoice.getInvoiceDate())
                                                || dateTo.getValue().isEqual(invoice.getInvoiceDate()))
                                                && (dateFrom.getValue().isBefore(invoice.getInvoiceDate())
                                                || dateFrom.getValue().isEqual(invoice.getInvoiceDate())))
                                .collect(Collectors.toList());

                        for (Invoice invoice : filtered) {
                            csvService.writeLine(writer, Arrays.asList(
                                    shopNumber.getText(),
                                    String.valueOf(invoice.getInvoiceNumber()),
                                    invoice.getInvoiceDate().toString(),
                                    invoice.getPIN() != null ? invoice.getPIN() : Constants.NO_PIN)
                            );

                            invoicesCount++;
                        }

                        writer.flush();
                        writer.close();

                        GBUtils.infoDialog(invoicesCount + " invoices exported").show();
                    } catch (IOException ex) {
                        //TODO add logging
                        ex.printStackTrace();
                    }
                } else {
                    GBUtils.errorDialog(Constants.FORM_NOT_COMPLETED).show();
                }
            }
        });
    }

    @FXML
    public void dateFromAction() {
        if (!validatorService.isDateInRange(dateFrom.getValue())) {
            dateFrom.setValue(null);
            dateFrom.requestFocus();
        } else if (!validatorService.isValidFromDate(dateFrom.getValue())) {
            GBUtils.errorDialog(Constants.DATE_IN_FUTURE).show();
            dateFrom.requestFocus();
        }

        filename.setText(GBUtils.generateFilename(shopNumber, dateFrom, dateTo));
    }

    @FXML
    public void dateToAction() {
        if (!validatorService.isDateInRange(dateTo.getValue())) {
            dateTo.setValue(null);
        } else if (!validatorService.isValidToDate(dateFrom.getValue(), dateTo.getValue())) {
            GBUtils.errorDialog(Constants.DATE_FROM_AFTER_TO).show();
            dateTo.requestFocus();
        } else {
            filename.setText(GBUtils.generateFilename(shopNumber, dateFrom, dateTo));
        }
    }
}
