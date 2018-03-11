package com.globalblue.commons;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public interface Constants {

    String VIEW_FOLDER = "/views/";
    String IMAGES_FOLDER = "/images/";

    String ROOT_VIEW = "main.fxml";
    String APP_TITLE = "Invoice Image PIN";
    String ICON = "globalblue.PNG";

    char DEFAULT_SEPARATOR = ',';

    String FILENAME_PREFIX = "PINExport_";
    String UNDERSCORE = "_";
    String CSV_EXTENSION = ".csv";

    String DATE_IN_FUTURE = "Date must not be in future";
    String DATE_FROM_AFTER_TO = "From-Date must be before To-Date";

    String MAX_LENGTH_REACHED = "Maximum length reached for this field";
    String CHARACTER_NOT_ALLOWED = "Character type not allowed in this field";

    String FORM_NOT_COMPLETED = "Form is not completed! Please fill all the fields!";

    String NO_PIN = "No PIN available";

    List<String> HEADERS = Arrays.asList("Invoice to ShopNr", "Invoice Number", "Invoice Date", "PIN");

    LocalDate MIN_DATE = LocalDate.of(1980, 1, 1);
    LocalDate MAX_DATE = LocalDate.of(2099, 1, 1);
}
