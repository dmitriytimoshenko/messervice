package com.example.messervice.entities;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlRootElement(name = "CD")
@XmlAccessorType(XmlAccessType.FIELD)
public class CD {

    @XmlElement(name = "TITLE")
    private String title;
    @XmlElement(name = "ARTIST")
    private String artist;
    @XmlElement(name = "COUNTRY")
    private String country;
    @XmlElement(name = "COMPANY")
    private String company;
    @XmlElement(name = "PRICE")
    private double price;
    @XmlElement(name = "YEAR")
    private int year;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
