package com.example.demo.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Costumer {
@GeneratedValue(strategy = GenerationType.AUTO)
@Id
    private Long id;
    private String name;
   private String firmName;
    private String address;
    private String city;
    private Integer postCode;
    private String email;


    public Costumer(String firmName,String name, String address, String city, Integer postCode, String email) {
        this.firmName=firmName;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.email = email;
    }

   public Costumer(){

   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
