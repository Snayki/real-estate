package com.real.estate.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Created by Snayki on 21.03.2016.
 */
@Document(indexName = "ads")
public class Ad implements Serializable {
    @Id
    private String content;

    private City city;

    private Integer numberOfRooms;

    private Integer floor;

    private Material typeOfMaterial;

    private Integer totalArea;
    private Integer livingSpace;

    private Integer price;
    private Integer pricePerSquareMeter;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Material getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(Material typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public Integer getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Integer totalArea) {
        this.totalArea = totalArea;
    }

    public Integer getLivingSpace() {
        return livingSpace;
    }

    public void setLivingSpace(Integer livingSpace) {
        this.livingSpace = livingSpace;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPricePerSquareMeter() {
        return pricePerSquareMeter;
    }

    public void setPricePerSquareMeter(Integer pricePerSquareMeter) {
        this.pricePerSquareMeter = pricePerSquareMeter;
    }
}
