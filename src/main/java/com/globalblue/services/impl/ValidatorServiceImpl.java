package com.globalblue.services.impl;

import com.globalblue.commons.Constants;
import com.globalblue.services.ValidatorService;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public boolean isValidFromDate(LocalDate validFrom) {

        if (validFrom.isBefore(LocalDate.now())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isValidToDate(LocalDate validFrom, LocalDate validTo) {

        if (validFrom != null && validFrom.isBefore(validTo)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isDateInRange(LocalDate date) {

        if (date != null
                && (date.isAfter(Constants.MIN_DATE) || date.isEqual(Constants.MIN_DATE))
                && (date.isBefore(Constants.MAX_DATE) || date.isEqual(Constants.MAX_DATE))) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFieldNotEmpty(Object field) {
        if (field instanceof javafx.scene.control.TextField) {
            javafx.scene.control.TextField _field = (javafx.scene.control.TextField) field;
            if (!_field.getText().isEmpty()) {
                return true;
            }
        } else if (field instanceof DatePicker) {
            LocalDate _field = ((DatePicker) field).getValue();
            if (_field != null && !_field.toString().isEmpty()) {
                return true;
            }
        }

        return false;
    }
}
