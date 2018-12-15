package com.example.demo.Model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

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
    private String invoiceDate;
    private String dueDate;
    @OneToMany
    private List<Invoice> invoices;


    public InvoiceCollection(){


    }

    public InvoiceCollection(Long invoiceId, Boolean paid, Long totalPris, String firmName, String email, String name, String invoiceDate, String dueDate, List<Invoice> invoices) {
        this.invoiceId = invoiceId;
        this.paid = paid;
        this.totalPris = totalPris;
        this.firmName = firmName;
        this.email = email;
        this.name = name;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}