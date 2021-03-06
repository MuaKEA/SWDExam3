package com.example.demo.Model;


import javax.persistence.*;

@Entity
public class Invoice {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private int unit;
    private int price;

    @OneToOne
    private Service service;
    @OneToOne
    private Customer customer;


    public Invoice(int unit,int price,Service service,Customer customer) {
       this.unit=unit;
       this.price=price;
       this.service=service;
       this.customer=customer;
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

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", unit=" + unit +
                ", price=" + price +
                ", service=" + service +
                ", customer=" + customer +
                '}';
    }
}
