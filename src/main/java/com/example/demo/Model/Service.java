package com.example.demo.Model;


import javax.persistence.*;

@Entity
public class Service {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private Long serviceId;
    private String name;
    private double price;

    public Service() {
    }

    public Service(Long serviceId,String name, double price) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
