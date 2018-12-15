package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Scheme {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
private String monDay;
private String tuesDay;
private String wednesday;
private String thursDay;
private String friDay;
private String saturDay;
private String sunDay;

public Scheme (){

}

    public Scheme(String monDay, String tuesDay, String wednesday, String thursDay, String friDay, String saturDay, String sunDay) {
        this.monDay = monDay;
        this.tuesDay = tuesDay;
        this.wednesday = wednesday;
        this.thursDay = thursDay;
        this.friDay = friDay;
        this.saturDay = saturDay;
        this.sunDay = sunDay;
    }

    public String getMonDay() {
        return monDay;
    }

    public void setMonDay(String monDay) {
        this.monDay = monDay;
    }

    public String getTuesDay() {
        return tuesDay;
    }

    public void setTuesDay(String tuesDay) {
        this.tuesDay = tuesDay;
    }

    public String getwednesday() {
        return wednesday;
    }

    public void setwednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursDay() {
        return thursDay;
    }

    public void setThursDay(String thursDay) {
        this.thursDay = thursDay;
    }

    public String getFriDay() {
        return friDay;
    }

    public void setFriDay(String friDay) {
        this.friDay = friDay;
    }

    public String getSaturDay() {
        return saturDay;
    }

    public void setSaturDay(String saturDay) {
        this.saturDay = saturDay;
    }

    public String getSunDay() {
        return sunDay;
    }

    public void setSunDay(String sunDay) {
        this.sunDay = sunDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
