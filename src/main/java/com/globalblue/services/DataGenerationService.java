package com.globalblue.services;

import com.globalblue.models.Invoice;
import javafx.collections.ObservableList;

import java.util.HashMap;

public interface DataGenerationService {

    HashMap<Integer, ObservableList<Invoice>> generateRecords(int shops, int invoicesPerShop);
}
