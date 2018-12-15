package com.example.demo.Model;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InvoiceWrapper {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceCalendar;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueCalendar;
    ArrayList<Invoice> invoiceArrayList= new ArrayList<>();




    public ArrayList<Invoice> getInvoiceArrayList() {
        return invoiceArrayList;
    }

    public void setInvoiceArrayList(ArrayList<Invoice> invoiceArrayList) {
        this.invoiceArrayList = invoiceArrayList;
    }

   public void addinvoice(Invoice invoice){
        this.invoiceArrayList.add(invoice);

   }

    public Date getInvoiceCalendar() {
        return invoiceCalendar;
    }

    public void setInvoiceCalendar(Date invoiceCalendar) {
        this.invoiceCalendar = invoiceCalendar;
    }

    public Date getDueCalendar() {
        return dueCalendar;
    }

    public void setDueCalendar(Date dueCalendar) {
        this.dueCalendar = dueCalendar;
    }
}
