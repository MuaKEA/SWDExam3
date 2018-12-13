package com.example.demo.Model;


import javax.persistence.*;

@Entity
public class Invoice {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private Long invoiceId;
    private int unit;
    private int price;
    private double totalPrice;
    private boolean payed;

    @OneToOne(cascade = {CascadeType.ALL})
    private Service service;
    @OneToOne(cascade = {CascadeType.ALL})
    private Customer customer;


    public Invoice(int unit,int price,double totalPrice,Boolean payed,Long invoiceId,Service service,Customer customer) {
       this.unit=unit;
       this.price=price;
       this.payed=payed;
        this.totalPrice = totalPrice;
        this.service=service;
        this.customer=customer;
        this.invoiceId=invoiceId;
    }

    public Invoice(){};

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public boolean getPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceId=" + invoiceId +
                ", unit=" + unit +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", payed=" + payed +
                ", service=" + service +
                ", customer=" + customer +
                '}';
    }
}
