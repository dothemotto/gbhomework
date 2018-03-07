package com.globalblue.models;

import java.time.LocalDate;

public class Invoice {
    private Long invoiceNumber;
    private LocalDate invoiceDate;
    private String PIN;

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber=" + invoiceNumber +
                ", invoiceDate=" + invoiceDate +
                ", PIN='" + PIN + '\'' +
                '}';
    }
}
