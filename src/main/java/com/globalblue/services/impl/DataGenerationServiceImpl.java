package com.globalblue.services.impl;

import com.globalblue.models.Invoice;
import com.globalblue.services.DataGenerationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerationServiceImpl implements DataGenerationService {

    @Override
    public HashMap<Integer, ObservableList<Invoice>> generateRecords(int shops, int invoicesPerShop) {
        HashMap<Integer, ObservableList<Invoice>> records = new HashMap<>();

        for (int shop = 1; shop < shops; ++shop) {
            records.put(shop, generateInvoiceList(invoicesPerShop));
        }

        return records;
    }

    private ObservableList<Invoice> generateInvoiceList(int listSize) {
        ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();

        for (int i = 0; i < listSize; ++i) {
            Invoice instance = new Invoice();

            instance.setInvoiceNumber(Long.parseLong(RandomStringUtils.randomNumeric(10)));
            instance.setInvoiceDate(generateLocalDate());
            instance.setPIN(RandomStringUtils.randomNumeric(4));

            invoiceList.add(instance);
        }

        return invoiceList;
    }

    private LocalDate generateLocalDate() {
        long minDay = LocalDate.of(2015, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2017, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
