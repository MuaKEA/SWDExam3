package com.example.demo.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class InvoiceCollection {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private Long invoiceId;
    private Boolean paid;
    private Long totalPris;
    private String firmName;
    private String email;
    private String name;
    @OneToMany
    private List<Invoice> invoices;


    public InvoiceCollection(){


    }

    public InvoiceCollection(Long invoiceId, Boolean paid, Long totalPris, String firmName, String email, String name, List<Invoice> invoices) {
        this.invoiceId = invoiceId;
        this.paid = paid;
        this.totalPris = totalPris;
        this.firmName = firmName;
        this.email = email;
        this.name = name;
        this.invoices = invoices;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Long getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(Long totalPris) {
        this.totalPris = totalPris;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

}