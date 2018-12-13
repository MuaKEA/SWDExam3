package com.example.demo.Model;

import java.util.ArrayList;

public class InvoiceWrapper {

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

}
