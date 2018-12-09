package com.example.demo.Model;

import javax.persistence.*;

@Entity
public class Invoice {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private int unit;
    private double totalPrice;
    @OneToOne
    private Service service;
    @OneToOne
    private Costumer costumer;


    public Invoice(int unit,double totalPrice,Service service,Costumer costumer) {
       this.unit=unit;
        this.totalPrice = totalPrice;
        this.service=service;
        this.costumer=costumer;
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

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", unit=" + unit +
                ", totalPrice=" + totalPrice +
                ", service=" + service +
                ", costumer=" + costumer +
                '}';
    }
}
