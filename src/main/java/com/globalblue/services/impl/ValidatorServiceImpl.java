package com.globalblue.services.impl;

import com.globalblue.services.ValidatorService;

import java.time.LocalDate;

public class ValidatorServiceImpl implements ValidatorService {

    final LocalDate min = LocalDate.of(1980, 1, 1);
    final LocalDate max = LocalDate.of(2099, 1, 1);

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

        if (date != null && date.isAfter(min) && date.isBefore(max)) {
            return true;
        }

        return false;
    }
}
