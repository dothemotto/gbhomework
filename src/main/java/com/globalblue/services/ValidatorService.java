package com.globalblue.services;

import java.time.LocalDate;

public interface ValidatorService {

    boolean isValidFromDate(LocalDate validFrom);

    boolean isValidToDate(LocalDate validFrom, LocalDate validTo);

    boolean isDateInRange(LocalDate date);
}
